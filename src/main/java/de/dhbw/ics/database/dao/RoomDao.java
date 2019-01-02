package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.RoomMapper;
import de.dhbw.ics.vo.Room;

import java.util.List;

public class RoomDao extends AbstractDao<Room> {
    private static final String PERSIST = "INSERT INTO ROOM(room_uuid, room_type, clean, vip_seats, room_number) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM ROOM WHERE room_uuid = ?";
    private static final String DELETE = "DELETE FROM ROOM WHERE room_uuid = ?";
    private static final String UPDATE = "UPDATE ROOM SET room_type = ?, clean = ?, vip_seats = ?, room_number = ? WHERE room_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM ROOM WHERE room_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM ROOM";

    @Override
    public boolean persist(Room object) {
        if (object != null) {
            return this.persistObject(Room.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getRoomType(), object.isClean(), object.isVIP(), object.getNumber());
        }
        return false;
    }

    @Override
    public Room get(Object key) {
        if (key != null) {
            return this.getObject(Room.class, SELECT, new Object[]{key}, new RoomMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Room.class, DELETE, key);
        }
        return false;
    }

    @Override
    public List<Room> getAll() {
        return this.getAllObjects(Room.class, SELECT_ALL, new RoomMapper());
    }

}
