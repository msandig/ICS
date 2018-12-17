package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {

    Reservation reservation = null;

    public TicketMapper() {
    }

    public TicketMapper(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("ticket_uuid");
        String seatUUID = resultSet.getString("seat_uuid");
        String pricecatUUID = resultSet.getString("pricecat_uuid");
        String presUUID = resultSet.getString("pres_uuid");

        Seat seat = new Seat(seatUUID, null, null, null, null);
        PriceCategory priceCategory = new PriceCategory(pricecatUUID, null, null, null, null, null);
        Presentation presentation = new Presentation(presUUID, null, null, null, null);
        Ticket ticket = new Ticket(uuid, seat, priceCategory, presentation);

        if (reservation != null) {
            String reservationUUID = resultSet.getString("res_uuid");
            if (reservationUUID != null && reservationUUID.equals(reservation.getUuid())) {
                ticket.setReservation(reservation);
                reservation.getTickets().add(ticket);
            }
        }
        return ticket;

    }
}
