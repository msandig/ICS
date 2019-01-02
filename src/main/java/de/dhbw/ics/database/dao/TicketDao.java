package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.TicketMapper;
import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.Ticket;

import java.util.List;

public class TicketDao extends AbstractDao<Ticket> {
    private static final String PERSIST = "INSERT INTO TICKET (ticket_uuid, res_uuid, seat_uuid, pricecat_uuid, pres_uuid) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM TICKET " +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid" +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid" +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid" +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid" +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid" +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATECORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid" +
            "JOIN SEAT_BUSY ON SEAT_BUSY.pres_uuid = TICKET.pres_uuid AND SEAT_BUSY.seat_uuid = TICKET.seat_uuid" +
            "WHERE TICKET.ticket_uuid = ?";

    private static final String DELETE = "DELETE FROM TICKET WHERE ticket_uuid = ?";
    private static final String UPDATE = "UPDATE TICKET SET res_uuid = ?, seat_uuid = ?, pricecat_uuid = ?, pres_uuid = ? WHERE ticket_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM TICKET WHERE ticket_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM TICKET" +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid" +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid" +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid" +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid" +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid" +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATECORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid" +
            "JOIN SEAT_BUSY ON SEAT_BUSY.pres_uuid = TICKET.pres_uuid AND SEAT_BUSY.seat_uuid = TICKET.seat_uuid";

    private static final String SELECT_ALL_BY_RESERVATION = "SELECT * FROM TICKET " +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid" +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid" +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid" +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid" +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid" +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATECORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid" +
            "JOIN SEAT_BUSY ON SEAT_BUSY.pres_uuid = TICKET.pres_uuid AND SEAT_BUSY.seat_uuid = TICKET.seat_uuid" +
            "WHERE TICKET.res_uuid = ?";

    private static final String DELETE_ALL_BY_RESERVATION = "DELETE FROM TICKET WHERE res_uuid = ?";

    @Override
    public boolean persist(Ticket object) {
        if (object != null) {
            return this.persistObject(Ticket.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getReservation().getUuid(), object.getSeat().getUuid(), object.getPriceCategory().getUuid(), object.getPresentation().getUuid());
        }
        return false;
    }

    @Override
    public Ticket get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(Ticket.class, SELECT, new Object[]{key}, new TicketMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Ticket.class, DELETE, key);
        }
        return false;
    }

    @Override
    public List<Ticket> getAll() {
        return this.getAllObjects(Ticket.class, SELECT_ALL, new TicketMapper());
    }

    public boolean getAllByReservation(Reservation reservation) {
        if (reservation != null) {
            List<Ticket> result = this.getObjectsByMultipleArguments(Ticket.class, SELECT, new Object[]{reservation.getUuid()}, new TicketMapper(reservation));
            return result != null;
        }
        return false;
    }

    public boolean deleteAllByReservation(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Ticket.class, DELETE_ALL_BY_RESERVATION, key);
        }
        return false;
    }
}
