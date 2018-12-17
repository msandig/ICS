package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.ReservationMapper;
import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.User;

import java.util.List;

public class ReservationDao extends AbstractDao<Reservation> {

    private static final String PERSIST = "INSERT INTO RESERVATION (res_uuid, user_uuid, date) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM RESERVATION WHERE res_uuid = ?";
    private static final String DELETE = "DELETE FROM RESERVATION WHERE role_uuid = ?";
    private static final String UPDATE = "UPDATE RESERVATION SET user_uuid = ?, date = ? WHERE res_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM RESERVATION WHERE res_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM RESERVATION";
    private static final String SELECT_ALL_BY_USER = "SELECT * FROM RESERVATION WHERE user_uuid = ?";
    private static final String DELETE_ALL_BY_USER = "DELETE FROM RESERVATION WHERE user_uuid = ?";

    @Override
    public boolean persist(Reservation object) {
        if (object instanceof Reservation && object != null) {
            Reservation reservation = object;
            return this.persistObject(Reservation.class, reservation.getUuid(), COUNT, UPDATE, PERSIST, reservation.getUuid(), reservation.getUser().getUuid(), reservation.getDate());
        }
        return false;
    }

    @Override
    public Reservation get(Object key) {
        return this.getObject(Reservation.class, SELECT, new Object[]{key}, new ReservationMapper());
    }

    public List<Reservation> getAll(User user) {
        return this.getObjectsByMultipleArguments(Reservation.class, SELECT_ALL_BY_USER, new Object[]{user.getUuid()}, new ReservationMapper(user));
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(Reservation.class, DELETE, key);
    }

    public boolean delete(User user) {
        return this.deleteObject(Reservation.class, DELETE_ALL_BY_USER, user.getUuid());
    }

    @Override
    public List<Reservation> getAll() {
        return this.getAllObjects(Reservation.class, SELECT_ALL, new ReservationMapper());
    }
}
