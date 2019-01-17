package de.dhbw.ics.manager;

import de.dhbw.ics.controller.web.ResultMessage;
import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
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

        User u = userDao.get(user.getEmail());
        if (u != null)
            return ResultMessage.USER_EXIST;

        if (user.getRole() == null && user.getPassword() == null) {
            user.setRole(roleDao.getByTitle("Guest"));
        } else if (user.getRole() == null) {
            user.setRole(roleDao.getByTitle("User"));
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
        return user;
    }

    public Object getReservation(String email, Integer resID) {
        Object result = this.checkForReservation(email, resID);
        if (result instanceof Presentation) {
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
            String password = user.getPassword();
            if (!Base64.isBase64(password.getBytes())) {
                if (!user.comparePassword(password))
                    return null;
            } else {
                if (!password.equals(user.getPassword()))
                    return null;
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
                PriceCategory priceCategory = this.priceCategoryDao.get(ticket.getPriceCategory());
                if (priceCategory == null || !priceCategory.equals(ticket.getPriceCategory()))
                    return ResultMessage.PRICE_CATEGORY_NOT_FOUND;
            }

            if (ticket.getSeat() != null) {
                Seat s = this.seatDao.get(ticket.getSeat().getUuid());
                if (s == null)
                    return ResultMessage.SEAT_NOT_FOUND;


                BusySeat busySeat = this.getBusySeat(p, s);
                if (busySeat != null && busySeat.isBusy()) {
                    return null;
                } else if (busySeat != null && busySeat.isLooked()) {
                    if (!busySeat.getSessionID().equals(sessionID)) {
                        int result = BusySeat.compareLockTimestamp(busySeat);
                        if (result > 0) {
                            return ResultMessage.LOCKED_SEAT;
                        }
                    }
                } else if (busySeat == null) {
                    busySeat = new BusySeat();
                    busySeat.setPresentation(p);
                    busySeat.setSeat(ticket.getSeat());
                }

                busySeat.setBusy(true);
                busySeat.setLooked(true);
                busySeat.setTimestamp(Calendar.getInstance().getTimeInMillis());
                busySeat.setSessionID(sessionID);

                persistBusySeats.add(busySeat);
                persistTickets.add(ticket);
            } else {
                return ResultMessage.MISSING_SEAT;
            }
        }

        if (!this.busySeatDao.persistBatch(persistBusySeats))
            return ResultMessage.COULD_NOT_BLOCK_SEATS;

        for (Ticket t : persistTickets) {
            if (!this.ticketDao.persist(t))
                return ResultMessage.COULD_NOT_PERSIST_TICKETS;
        }

        if (!this.reservationDao.persist(reservation)) {
            for (Ticket t : persistTickets) {
                this.ticketDao.deleteAllByReservation(reservation);
            }
            for (BusySeat bs : persistBusySeats) {
                bs.setLooked(false);
                bs.setSessionID("");
                bs.setBusy(false);
            }
            this.busySeatDao.persistBatch(persistBusySeats);
            return ResultMessage.COULD_NOT_PERSIST_RESERVATION;
        }

        Reservation result = this.reservationDao.get(reservation.getUuid());
        if (result != null) {
            this.ticketDao.getAllByReservation(result);
        }
        return result;
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
            busySeat.setLooked(false);
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

        Reservation reservation = this.reservationDao.get(resID);
        if (reservation == null)
            return ResultMessage.RESERVATION_IS_NULL;

        if (!reservation.getUser().getUuid().equals(user.getUuid()))
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

                if (bs.isLooked() && !bs.getSessionID().equals(sessionID)) {
                    int offset = BusySeat.compareLockTimestamp(bs);
                    if (offset > 0) {
                        return ResultMessage.LOCKED_SEAT;
                    }
                }
            }

            BusySeat busySeat = new BusySeat();
            busySeat.setBusy(false);
            busySeat.setLooked(true);
            busySeat.setSessionID(sessionID);
            busySeat.setTimestamp(Calendar.getInstance().getTimeInMillis());
            busySeat.setPresentation(presentation);
            busySeat.setSeat(s);
            bsUpdate.add(busySeat);
            s.setCurrentBusySeat(busySeat);
            s.addBusy(busySeat);
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
        Object result = checkForLocking(uuid, seats, sessionID);
        if (result instanceof Presentation) {
            presentation = (Presentation) result;
        } else {
            return ResultMessage.PRESENTATION_NOT_FOUND;
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

                if (bs.isLooked() && bs.getSessionID().equals(sessionID)) {
                    bs.setLooked(false);
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
        return null;
    }

    private Object checkForLocking(String uuid, List<Seat> seats, String sessionID) {
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