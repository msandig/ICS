package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {

    private Reservation reservation = null;

    public TicketMapper() {
    }

    public TicketMapper(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("ticket_uuid");
        Presentation presentation = new PresentationMapper().mapRow(resultSet, i);
        Seat seat;
        if(presentation != null) {
            seat = new SeatMapper(presentation.getRoom()).mapRow(resultSet, i);
        }else {
            seat = new SeatMapper().mapRow(resultSet, i);
        }
        PriceCategory priceCategory = new PriceCategoryMapper().mapRow(resultSet, i);
        Ticket ticket = new Ticket(uuid, seat, priceCategory, presentation);

        if (reservation != null) {
            Integer reservationUUID = resultSet.getInt("res_uuid");
            if (reservationUUID.equals(reservation.getUuid())) {
                ticket.setReservation(reservation);
                reservation.getTickets().add(ticket);
            }
        }
        return ticket;

    }
}
