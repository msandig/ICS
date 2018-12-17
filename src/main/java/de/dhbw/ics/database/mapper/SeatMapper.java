package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;
import de.dhbw.ics.vo.SeatCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements RowMapper<Seat> {

    private Room room = null;

    public SeatMapper(Room room) {
        this.room = room;
    }

    @Override
    public Seat mapRow(ResultSet resultSet, int i) throws SQLException {
        if (room == null) {
            throw new NullPointerException("Room for SeatMapper is null!");
        }
        SeatCategory seatCategory = new SeatCategoryMapper().mapRow(resultSet, i);
        Integer number = resultSet.getInt("number");
        Integer row = resultSet.getInt("row");
        String uuid = resultSet.getString("seat_uuid");
        Seat seat = new Seat(uuid, room, seatCategory, number, row);
        room.getSeats().add(seat);
        return seat;
    }
}
