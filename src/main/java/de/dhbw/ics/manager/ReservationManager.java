package de.dhbw.ics.manager;

import de.dhbw.ics.controller.web.ResultMessage;
import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ReservationManager {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    SeatDao seatDao;

    @Autowired
    BusySeatDao busySeatDao;

    @Autowired
    PresentationDao presentationDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    RoomDao roomDao;

    @Autowired
    PriceCategoryDao priceCategoryDao;

    public Object persistUser(User user) {

        if (user.getEmail() == null || !EmailValidator.getInstance().isValid(user.getEmail())) {
            return ResultMessage.WRONG_EMAIL;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty())
            return ResultMessage.MISSING_FIRSTNAME;

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            return ResultMessage.MISSING_LASTNAME;
        }

        User u = this.userDao.get(user.getEmail());
        if (u != null) {
            if ((u.getPassword() == null || u.getPassword().isEmpty()) && u.getRole().getTitle().equals("Guest")) {
                this.mapReservations(u);
                return u;
            } else if(user.getPassword() == null || user.getPassword().equals(StringUtils.EMPTY)){
                return ResultMessage.USER_NEEDS_PASSWORD;
            } else if (!user.getPassword().equals(u.getPassword()) && !u.comparePassword(user.getPassword())){
                return ResultMessage.USER_NEEDS_PASSWORD;
            } else {
                this.mapReservations(u);
                return u;
            }
        }

        if (user.getRole() == null && (user.getPassword() == null || user.getPassword().equals(StringUtils.EMPTY))) {
            user.setRole(this.roleDao.getByTitle("Guest"));
        } else if (user.getRole() == null) {
            user.setRole(this.roleDao.getByTitle("User"));
        }

        if (this.userDao.persist(user))
            return this.userDao.get(user.getEmail());

        return ResultMessage.COULD_NOT_PERSIST_USER;
    }

    public Object getUser(String email, String password) {
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            return ResultMessage.WRONG_EMAIL;
        }
        User user = this.userDao.get(email);

        if (password != null && !password.isEmpty()) {
            if (Base64.isBase64(password)) {
                if (!password.equals(user.getPassword())) {
                    return ResultMessage.WRONG_PASSWORD;
                }
            } else {
                if (!user.comparePassword(password)) {
                    return ResultMessage.WRONG_PASSWORD;
                }
            }
        } else if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            return ResultMessage.WRONG_PASSWORD;
        }
        this.mapReservations(user);
        return user;
    }

    private void mapReservations(User user) {
        this.reservationDao.getAllByUser(user);
        if (user.getReservationList() != null && user.getReservationList().size() != 0) {
            user.getReservationList().forEach(r -> this.ticketDao.getAllByReservation(r));
        }
    }

    public Object getReservation(String email, Integer resID) {
        Object result = this.checkForReservation(email, resID);
        if (result instanceof Reservation) {
            this.ticketDao.getAllByReservation((Reservation) result);
        }
        return result;
    }

    public Object persistReservation(Reservation reservation, String sessionID) {
        if (reservation == null)
            return ResultMessage.RESERVATION_IS_NULL;

        if (reservation.getTickets() == null || reservation.getTickets().size() == 0)
            return ResultMessage.MISSING_TICKETS;

        if (reservation.getUser() == null || reservation.getUser().getEmail() == null || !EmailValidator.getInstance().isValid(reservation.getUser().getEmail()))
            return ResultMessage.MISSING_USER;

        User user = this.userDao.get(reservation.getUser().getEmail());

        if (user == null)
            return ResultMessage.USER_NOT_FOUND;

        if (!user.getRole().getTitle().equals("Guest")) {
            String password = reservation.getUser().getPassword();
            if(password == null){
                return  ResultMessage.USER_NEEDS_PASSWORD;
            }
            if (!Base64.isBase64(password.getBytes())) {
                if (!user.comparePassword(password))
                    return ResultMessage.WRONG_PASSWORD;
            } else {
                if (!password.equals(user.getPassword()))
                    return ResultMessage.WRONG_PASSWORD;
            }
        }

        List<Ticket> tickets = reservation.getTickets();
        List<Ticket> persistTickets = new ArrayList<>();
        List<BusySeat> persistBusySeats = new ArrayList<>();

        for (Ticket ticket : tickets) {
            Presentation p;
            if (ticket.getPresentation() != null) {
                p = this.presentationDao.get(ticket.getPresentation().getUuid());
                if (p == null)
                    return ResultMessage.PRESENTATION_NOT_FOUND;
            } else {
                return ResultMessage.PRESENTATION_NOT_SET;
            }
            Room r;
            if (ticket.getPresentation().getRoom() != null) {
                r = this.roomDao.get(ticket.getPresentation().getRoom().getUuid());
            } else if (ticket.getSeat().getRoom() != null) {
                r = this.roomDao.get(ticket.getSeat().getRoom().getUuid());
            } else {
                return ResultMessage.MISSING_ROOM;
            }

            if (r == null)
                return ResultMessage.ROOM_NOT_FOUND;

            if (ticket.getPriceCategory() == null) {
                return ResultMessage.MISSING_PRICE_CATEGORY;
            } else {
                PriceCategory priceCategory = this.priceCategoryDao.get(ticket.getPriceCategory().getUuid());
                if (priceCategory == null)
                    return ResultMessage.PRICE_CATEGORY_NOT_FOUND;
            }

            if (ticket.getSeat() != null) {
                Seat s = this.seatDao.get(ticket.getSeat());
                if (s == null)
                    return ResultMessage.SEAT_NOT_FOUND;

                BusySeat busySeat = this.getBusySeat(p, s);
                if (busySeat != null && busySeat.isBusy()) {

                    return ResultMessage.SEAT_TAKEN;

                } else if (busySeat != null && busySeat.isLocked()) {
                    if (!busySeat.getSessionID().equals(sessionID)) {
                        int result = BusySeat.compareLockTimestamp(busySeat);
                        if (result > 0) {
                            return ResultMessage.LOCKED_SEAT;
                        }
                    }
                }

                if (busySeat == null) {
                    busySeat = new BusySeat(true, s, p, true, sessionID, Calendar.getInstance().getTimeInMillis());
                } else {
                    busySeat.setBusy(true);
                    busySeat.setLocked(true);
                    busySeat.setTimestamp(Calendar.getInstance().getTimeInMillis());
                    busySeat.setSessionID(sessionID);
                }

                persistBusySeats.add(busySeat);
                persistTickets.add(ticket);
            } else {
                return ResultMessage.MISSING_SEAT;
            }
        }

        if (persistBusySeats.size() != reservation.getTickets().size() || persistBusySeats.size() != reservation.getTickets().size())
            return ResultMessage.COULD_NOT_PERSIST_RESERVATION;

        if (reservation.getDate() == 0) {
            reservation.setDate(Calendar.getInstance().getTimeInMillis());
        }
        if (this.reservationDao.persist(reservation)) {
            boolean error = false;
            for (Ticket t : persistTickets) {
                if (!this.ticketDao.persist(t)) {
                    error = true;
                    break;
                }
            }
            if (!error) {
                error = !this.busySeatDao.persistBatch(persistBusySeats);
            }

            if (error) {
                for (BusySeat bs : persistBusySeats) {
                    bs.setLocked(false);
                    bs.setSessionID("");
                    bs.setBusy(false);
                }
                this.busySeatDao.persistBatch(persistBusySeats);

                for (Ticket t : persistTickets) {
                    this.ticketDao.deleteAllByReservation(reservation);
                }
                return ResultMessage.COULD_NOT_PERSIST_RESERVATION;
            }

            Reservation result = this.reservationDao.get(reservation.getUuid());
            if (result != null) {
                this.ticketDao.getAllByReservation(result);
                result.setUser(user);
                return result;
            }
        }
        return ResultMessage.COULD_NOT_PERSIST_RESERVATION;
    }

    public Object deleteReservation(String email, Integer resID) {
        Object checkObject = this.checkForReservation(email, resID);
        Reservation reservation;
        if (checkObject instanceof Reservation) {
            reservation = (Reservation) checkObject;
        } else {
            return checkObject;
        }
        this.ticketDao.getAllByReservation(reservation);

        for (Ticket ticket : reservation.getTickets()) {
            Map<String, Object> map = new HashMap<>();
            map.put("p", ticket.getPresentation());
            map.put("s", ticket.getSeat());

            BusySeat busySeat = this.busySeatDao.get(map);
            busySeat.setLocked(false);
            busySeat.setBusy(false);

            if (this.busySeatDao.persist(busySeat))
                return ResultMessage.COULD_NOT_DELETE_RESERVATION;

            if (ticketDao.delete(ticket.getUuid()))
                return ResultMessage.COULD_NOT_DELETE_RESERVATION;
        }
        if (!this.reservationDao.delete(reservation.getUuid())) {
            return ResultMessage.COULD_NOT_DELETE_RESERVATION;
        } else {
            return ResultMessage.SUCCESS;
        }
    }


    private Object checkForReservation(String email, Integer resID) {
        if (email == null || !EmailValidator.getInstance().isValid(email))
            return ResultMessage.WRONG_EMAIL;

        if (resID == 0)
            return ResultMessage.RESERVATION_IS_NULL;

        User user = this.userDao.get(email);
        if (user == null)
            return ResultMessage.USER_NOT_FOUND;

        Reservation reservation = this.reservationDao.getByNumber(resID);
        if (reservation == null)
            return ResultMessage.RESERVATION_IS_NULL;

        if (reservation.getUser() == null || !reservation.getUser().getUuid().equals(user.getUuid()))
            return ResultMessage.RESERVATION_USER_UNMATCHING;

        reservation.setUser(user);
        return reservation;
    }

    private Object lockSeats(List<Seat> seats, String sessionID, Presentation presentation) {
        List<BusySeat> bsUpdate = new ArrayList<>();
        for (Seat s : seats) {
            BusySeat bs = getBusySeat(presentation, s);
            if (bs != null) {
                if (bs.isBusy())
                    return ResultMessage.SEAT_TAKEN;

                if (bs.isLocked() && !bs.getSessionID().equals(sessionID)) {
                    int offset = BusySeat.compareLockTimestamp(bs);
                    if (offset > 0) {
                        return ResultMessage.LOCKED_SEAT;
                    }
                }

                bs.setBusy(false);
                bs.setLocked(true);
                bs.setSessionID(sessionID);
                bs.setTimestamp(Calendar.getInstance().getTimeInMillis());
                s.setCurrentBusySeat(bs);
                s.addBusy(bs);
                bsUpdate.add(bs);
                continue;
            }

            bs = new BusySeat(false, s, presentation, true, sessionID, Calendar.getInstance().getTimeInMillis());
            bsUpdate.add(bs);
            s.setCurrentBusySeat(bs);
            s.addBusy(bs);
        }
        if (bsUpdate.size() == seats.size()) {
            if (this.busySeatDao.persistBatch(bsUpdate)) {
                return seats;
            }
        }
        return ResultMessage.COULD_NOT_BLOCK_SEATS;
    }

    public Object lockSeats(String uuid, List<Seat> seats, String sessionID, boolean locking) {
        Presentation presentation;
        Object result = checkForPresentation(uuid, seats, sessionID);
        if (result instanceof Presentation) {
            presentation = (Presentation) result;
        } else {
            return result;
        }
        if (locking) {
            return this.lockSeats(seats, sessionID, presentation);
        } else {
            return this.unlockSeats(seats, sessionID, presentation);
        }

    }

    private Object unlockSeats(List<Seat> seats, String sessionID, Presentation presentation) {
        List<BusySeat> bsUpdate = new ArrayList<>();
        for (Seat s : seats) {
            BusySeat bs = getBusySeat(presentation, s);
            if (bs != null) {
                if (bs.isBusy())
                    return ResultMessage.SEAT_TAKEN;

                if (bs.isLocked() && bs.getSessionID().equals(sessionID)) {
                    bs.setLocked(false);
                    bs.setTimestamp(0);
                    bs.setSessionID("");
                    bsUpdate.add(bs);
                    s.setCurrentBusySeat(bs);
                    s.addBusy(bs);
                }
            }
        }
        if (bsUpdate.size() == seats.size()) {
            if (this.busySeatDao.persistBatch(bsUpdate)) {
                return seats;
            }
        }
        return ResultMessage.COULD_NOT_UNBLOCK_SEATS;
    }

    private Object checkForPresentation(String uuid, List<Seat> seats, String sessionID) {
        if (uuid == null || uuid.isEmpty())
            return ResultMessage.MISSING_PRESENTATION_ID;

        if (seats == null || seats.size() == 0)
            return ResultMessage.NO_SEAT_TO_LOCK;

        if (sessionID == null || sessionID.isEmpty())
            return ResultMessage.MISSING_SESSION_ID;


        Presentation presentation = this.presentationDao.get(uuid);
        if (presentation == null)
            return ResultMessage.PRESENTATION_NOT_FOUND;

        for (Seat s : seats) {
            Seat seat = this.seatDao.get(s);
            if (seat == null || !seat.equals(s))
                return ResultMessage.SEAT_NOT_FOUND;
        }

        return presentation;
    }

    private BusySeat getBusySeat(Presentation presentation, Seat seat) {
        Map<String, Object> map = new HashMap<>();
        map.put("p", presentation);
        map.put("s", seat);
        return this.busySeatDao.get(map);
    }

}
