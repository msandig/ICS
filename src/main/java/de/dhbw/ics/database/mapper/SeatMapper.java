package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;
import de.dhbw.ics.vo.SeatCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatMapper implements RowMapper<Seat> {

    private Room globalRoom = null;
    private List<Room> roomList = new ArrayList<>();

    public SeatMapper() {}

    public SeatMapper(Room room) {
        this.globalRoom = room;
    }

    @Override
    public Seat mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room;
        if (this.globalRoom == null || !this.globalRoom.getUuid().equals(resultSet.getString("room_uuid"))) {
            var r = roomList.stream().filter(x -> x.getUuid().equals(globalRoom.getUuid())).collect(Collectors.toList());
            if (r.size() == 0) {
                room = new RoomMapper().mapRow(resultSet, i);
                roomList.add(room);
                this.globalRoom = room;
            } else {
                this.globalRoom = r.get(0);
            }
        }
        SeatCategory seatCategory = new SeatCategoryMapper().mapRow(resultSet, i);
        Integer number = resultSet.getInt("number");
        Integer row = resultSet.getInt("row");
        String uuid = resultSet.getString("seat_uuid");
        Seat seat = new Seat(uuid, this.globalRoom, seatCategory, number, row);
        this.globalRoom.getSeats().put(seat.getUuid(), seat);
        return seat;
    }
}
