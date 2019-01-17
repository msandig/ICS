package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.SeatMapper;
import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;

import java.util.List;

public class SeatDao extends AbstractDao<Seat> {

    private static final String PERSIST = "INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT SEAT.seat_uuid as seat_uuid, SEAT.seatcat_uuid as seatcat_uuid, SEAT.room_uuid as room_uuid, " +
            "SEAT.number as number, SEAT.row as row, SEAT_CATEGORY.seatcat_title as seatcat_title, SEAT_CATEGORY.seatcat_description as seatcat_description, " +
            "ROOM.room_type as room_type, ROOM.clean as clean, ROOM.vip_seats as vip_seats, ROOM.room_number as room_number " +
            "FROM SEAT JOIN SEAT_CATEGORY ON SEAT.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid JOIN ROOM ON SEAT.room_uuid = ROOM.room_uuid WHERE SEAT.seat_uuid = ?";
    private static final String DELETE = "DELETE FROM SEAT WHERE seat_uuid = ?";
    private static final String UPDATE = "UPDATE SEAT SET row = ?, number = ?, seatcat_uuid = ?, room_uuid = ? WHERE seat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM SEAT WHERE seat_uuid = ?";
    private static final String SELECT_ALL = "SELECT SEAT.seat_uuid as seat_uuid, SEAT.seatcat_uuid as seatcat_uuid, SEAT.room_uuid as room_uuid, " +
            "SEAT.number as number, SEAT.row as row, SEAT_CATEGORY.seatcat_title as seatcat_title, SEAT_CATEGORY.seatcat_description as seatcat_description, " +
            "ROOM.room_type as room_type, ROOM.clean as clean, ROOM.vip_seats as vip_seats, ROOM.room_number as room_number " +
            "FROM SEAT JOIN SEAT_CATEGORY ON SEAT.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid JOIN ROOM ON SEAT.room_uuid = ROOM.room_uuid";
    private static final String SELECT_ALL_BY_ROOM = "SELECT SEAT.seat_uuid as seat_uuid, SEAT.seatcat_uuid as seatcat_uuid, SEAT.room_uuid as room_uuid, " +
            "SEAT.number as number, SEAT.row as row, SEAT_CATEGORY.seatcat_title as seatcat_title, SEAT_CATEGORY.seatcat_description as seatcat_description " +
            "FROM SEAT JOIN SEAT_CATEGORY ON SEAT.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid WHERE SEAT.room_uuid = ?";

    @Override
    public boolean persist(Seat object) {
        if (object != null) {
            return this.persistObject(Seat.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getRow(), object.getNumber(), object.getSeatCategory().getUuid(), object.getRoom().getUuid());
        }
        return false;
    }

    @Override
    public Seat get(Object seat) {
        if (seat instanceof Seat) {
            Seat s = (Seat) seat;
            return this.getObject(Seat.class, SELECT, new Object[]{s.getUuid()}, new SeatMapper(s.getRoom()));
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Seat.class, DELETE, new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Seat> getAll() {
        return this.getAllObjects(Seat.class, SELECT_ALL, new SeatMapper());
    }

    public List<Seat> getAllByRoom(Room room) {
        if (room != null) {
            return this.getObjectsByMultipleArguments(Seat.class, SELECT_ALL_BY_ROOM, new Object[]{room.getUuid()}, new SeatMapper(room));
        }
        return null;
    }

}
