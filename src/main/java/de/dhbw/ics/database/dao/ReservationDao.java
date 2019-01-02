package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.ReservationMapper;
import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.User;

import java.util.List;

public class ReservationDao extends AbstractDao<Reservation> {

    private static final String PERSIST = "INSERT INTO RESERVATION (res_uuid, user_uuid, date, payed) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM RESERVATION WHERE res_uuid = ?";
    private static final String DELETE = "DELETE FROM RESERVATION WHERE res_uuid = ?";
    private static final String UPDATE = "UPDATE RESERVATION SET user_uuid = ?, date = ?, payed = ? WHERE res_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM RESERVATION WHERE res_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM RESERVATION";
    private static final String SELECT_ALL_BY_USER = "SELECT * FROM RESERVATION WHERE user_uuid = ?";
    private static final String DELETE_ALL_BY_USER = "DELETE FROM RESERVATION WHERE user_uuid = ?";

    @Override
    public boolean persist(Reservation object) {
        if (object != null) {
            return this.persistObject(Reservation.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getUser().getUuid(), object.getDate());
        }
        return false;
    }

    @Override
    public Reservation get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(Reservation.class, SELECT, new Object[]{key}, new ReservationMapper());
        }
        return null;
    }

    public List<Reservation> getAllByUser(User user) {
        return this.getObjectsByMultipleArguments(Reservation.class, SELECT_ALL_BY_USER, new Object[]{user.getUuid()}, new ReservationMapper(user));
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Reservation.class, DELETE, key);
        }
        return false;
    }

    public boolean deleteAllByUser(User user) {
        if (user != null) {
            return this.deleteObject(Reservation.class, DELETE_ALL_BY_USER, user.getUuid());
        }
        return false;
    }

    @Override
    public List<Reservation> getAll() {
        return this.getAllObjects(Reservation.class, SELECT_ALL, new ReservationMapper());
    }
}
