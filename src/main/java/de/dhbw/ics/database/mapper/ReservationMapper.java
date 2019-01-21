package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {

    private User user;

    public ReservationMapper(){}

    public ReservationMapper(User user){
        this.user = user;
    }

    @Override
    public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer number = resultSet.getInt("res_number");
        String uuid = resultSet.getString("res_uuid");
        String userUUID = resultSet.getString("user_uuid");
        long date = resultSet.getLong("date");
        boolean payed = resultSet.getBoolean("payed");
        Reservation reservation = new Reservation(uuid, number, date, payed);
        if(this.user != null){
            if (userUUID.equals(this.user.getUuid())){
                reservation.setUser(this.user);
                this.user.getReservationList().add(reservation);
            }
        }else{
            User user = new User(userUUID);
            reservation.setUser(user);
        }
        return reservation;
    }
}
