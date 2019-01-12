package de.dhbw.ics.manager;

import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public User getUser(String id) {
        if (id != null && !id.isEmpty())
            return userDao.get(id);

        return null;

    }

    public User persistUser(User user) {

        if (user.getEmail() == null || !EmailValidator.getInstance().isValid(user.getEmail())) {
            return null;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty())
            return null;

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            return null;
        }

        if (user.getRole() == null && user.getPassword() == null) {
            user.setRole(roleDao.getByTitle("Guest"));
        } else if (user.getRole() == null) {
            user.setRole(roleDao.getByTitle("User"));
        }
        if(this.userDao.persist(user))
            return user;

        return null;
    }

    public User getUser(String email, String password) {
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            return null;
        }
        User user = this.userDao.get(email);

        if (password != null && !password.isEmpty()) {
            if (Base64.isBase64(password)) {
                if (!password.equals(user.getPassword())) {
                    return null;
                }
            } else {
                if (!user.comparePassword(password)) {
                    return null;
                }
            }
        } else if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            return null;
        }
        return user;
    }

    public Reservation getReservation(String email, Integer resID) {
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            return null;
        }

        if (resID == 0) {
            return null;
        }

        User user = this.userDao.get(email);
        if (user == null)
            return null;

        Reservation reservation = this.reservationDao.get(resID);
        if (reservation == null)
            return null;

        if (!reservation.getUser().getUuid().equals(user.getUuid()))
            return null;

        return reservation;
    }

    public Reservation persistReservation(Reservation reservation) {
        return null;

    }

    public List<Seat> lockSeats(String uuid, List<Seat> seats, String sessionID) {
        Presentation presentation = checkForLocking(uuid, seats, sessionID);
        if (presentation == null)
            return null;

        for (Seat s : seats) {
            BusySeat bs = getBusySeat(presentation, s);
            if (bs != null) {
                if (bs.isBusy())
                    return null;

                if (bs.isLooked() && !bs.getSessionID().equals(sessionID)) {
                    int result = BusySeat.compareLockTimestamp(bs);
                    if (result <= 0) {
                        return null;
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
            if (this.busySeatDao.persist(busySeat)) {
                s.setCurrentBusySeat(busySeat);
                s.addBusy(busySeat);
            } else {
                return null;
            }
        }

        return seats;
    }

    public List<Seat> unlockSeats(String uuid, List<Seat> seats, String sessionID) {

        Presentation presentation = checkForLocking(uuid, seats, sessionID);
        if (presentation == null)
            return null;

        for (Seat s : seats) {
            BusySeat bs = getBusySeat(presentation, s);
            if (bs != null) {
                if (bs.isBusy())
                    return null;
                if (bs.isLooked() && bs.getSessionID().equals(sessionID)) {
                    bs.setLooked(false);
                    bs.setTimestamp(0);
                    bs.setSessionID("");
                    this.busySeatDao.persist(bs);
                }
            }
        }
        return seats;
    }

    private Presentation checkForLocking(String uuid, List<Seat> seats, String sessionID) {
        if (uuid == null || uuid.isEmpty())
            return null;

        if (seats == null || seats.size() == 0)
            return null;

        if (sessionID == null || sessionID.isEmpty())
            return null;


        Presentation presentation = this.presentationDao.get(uuid);
        if (presentation == null)
            return null;

        for (Seat s : seats) {
            Seat seat = this.seatDao.get(s.getUuid());
            if (seat == null || !seat.equals(s))
                return null;
        }

        return presentation;
    }

    private BusySeat getBusySeat(Presentation presentation, Seat seat) {
        Map<String, Object> map = new HashMap<>();
        map.put("p", presentation);
        map.put("s", seat);
        BusySeat bs = this.busySeatDao.get(map);
        return bs;
    }

}
