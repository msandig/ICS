package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReservationMapper implements RowMapper<Reservation> {

    private User user;

    public ReservationMapper(){}

    public ReservationMapper(User user){
        this.user = user;
    }

    @Override
    public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer uuid = resultSet.getInt("res_uuid");
        String userUUID = resultSet.getString("user_uuid");
        Date date = resultSet.getDate("date");
        Reservation reservation = new Reservation(uuid, date);
        if(this.user != null){
            if (userUUID == this.user.getUuid()){
                reservation.setUser(this.user);
            }
        }else{
            User user = new User(userUUID);
            reservation.setUser(user);
        }
        return reservation;
    }
}
