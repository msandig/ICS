package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;

import java.util.List;

public class SeatDao extends AbstractDao<Seat> {

    private static final String PERSIST = "INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM SEAT JOIN SEAT_CATEGORY ON SEAT.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid WHERE seat_uuid = ?";
    private static final String DELETE = "DELETE FROM SEAT WHERE seat_uuid = ?";
    private static final String UPDATE = "UPDATE SEAT SET row = ?, number = ?, seatcat_uuid = ?, room_uuid = ? WHERE seat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM SEAT WHERE seat_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM SEAT";
    private static final String SELECT_ALL_BY_ROOM = "SELECT * FROM SEAT WHERE room_uuid = ?";

    private Room room = null;

    @Override
    public boolean persist(Seat object) {
        if (object != null) {
            return this.persistObject(Seat.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getRow(), object.getNumber(), object.getSeatCategory().getUuid(), object.getRoom().getUuid());
        }
        return false;
    }

    @Override
    public Seat get(Object key) {
        return null;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }

    @Override
    public List<Seat> getAll() {
        return null;
    }

    public List<Seat> getAll(Room room) {
        return null;
    }
}
