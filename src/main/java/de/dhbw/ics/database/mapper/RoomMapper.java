package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("room_uuid");
        String roomType = resultSet.getString("roomType");
        boolean clean = resultSet.getBoolean("clean");
        boolean vip = resultSet.getBoolean("vip_seats");
        int number = resultSet.getInt("room_number");
        return new Room(uuid, roomType, clean, vip, number);
    }
}
