package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.TicketMapper;
import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.Ticket;

import java.util.List;

public class TicketDao extends AbstractDao<Ticket> {
    private static final String PERSIST = "INSERT INTO TICKET (ticket_uuid, res_uuid, seat_uuid, pricecat_uuid, pres_uuid) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT TICKET.ticket_uuid as ticket_uuid, TICKET.res_uuid as res_uuid, TICKET.seat_uuid as seat_uuid, TICKET.pricecat_uuid as pricecat_uuid, " +
            "TICKET.pres_uuid as pres_uuid, SEAT.number as number, SEAT.row as row, PRICE_CATEGORY.prescat_uuid as prescat_uuid, PRICE_CATEGORY.seatcat_uuid as seatcat_uuid, " +
            "PRICE_CATEGORY.pricecat_title as pricecat_title, PRICE_CATEGORY.pricecat_description as pricecat_description, PRICE_CATEGORY.price as price, " +
            "PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, ROOM.room_type as room_type, ROOM.clean as clean, " +
            "ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, SEAT_CATEGORY.seatcat_description as seatcat_description, SEAT_CATEGORY.seatcat_title as seatcat_title, " +
            "MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, MOVIE.movie_title as movie_title , MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description, BUSY_SEAT.busy as busy, BUSY_SEAT.looked as looked " +
            "FROM TICKET " +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid " +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid " +
            "JOIN ROOM ON ROOM.room_uuid = PRESENTATION.room_uuid " +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid " +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid " +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATEGORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid " +
            "JOIN BUSY_SEAT ON BUSY_SEAT.pres_uuid = TICKET.pres_uuid AND BUSY_SEAT.seat_uuid = TICKET.seat_uuid " +
            "WHERE TICKET.ticket_uuid = ?";

    private static final String DELETE = "DELETE FROM TICKET WHERE ticket_uuid = ?";
    private static final String UPDATE = "UPDATE TICKET SET res_uuid = ?, seat_uuid = ?, pricecat_uuid = ?, pres_uuid = ? WHERE ticket_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM TICKET WHERE ticket_uuid = ?";
    private static final String SELECT_ALL = "SELECT TICKET.ticket_uuid as ticket_uuid, TICKET.res_uuid as res_uuid, TICKET.seat_uuid as seat_uuid, TICKET.pricecat_uuid as pricecat_uuid, " +
            "TICKET.pres_uuid as pres_uuid, SEAT.number as number, SEAT.row as row, PRICE_CATEGORY.prescat_uuid as prescat_uuid, PRICE_CATEGORY.seatcat_uuid as seatcat_uuid, " +
            "PRICE_CATEGORY.pricecat_title as pricecat_title, PRICE_CATEGORY.pricecat_description as pricecat_description, PRICE_CATEGORY.price as price, " +
            "PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, ROOM.room_type as room_type, ROOM.clean as clean, " +
            "ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, SEAT_CATEGORY.seatcat_description as seatcat_description, SEAT_CATEGORY.seatcat_title as seatcat_title, " +
            "MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, MOVIE.movie_title as movie_title , MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description, BUSY_SEAT.busy as busy, BUSY_SEAT.looked as looked " +
            "FROM TICKET " +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid " +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid " +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid " +
            "JOIN ROOM ON ROOM.room_uuid = PRESENTATION.room_uuid " +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid " +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATEGORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid " +
            "JOIN BUSY_SEAT ON BUSY_SEAT.pres_uuid = TICKET.pres_uuid AND BUSY_SEAT.seat_uuid = TICKET.seat_uuid ";

    private static final String SELECT_ALL_BY_RESERVATION = "SELECT TICKET.ticket_uuid as ticket_uuid, TICKET.res_uuid as res_uuid, TICKET.seat_uuid as seat_uuid, TICKET.pricecat_uuid as pricecat_uuid, " +
            "TICKET.pres_uuid as pres_uuid, SEAT.number as number, SEAT.row as row, PRICE_CATEGORY.prescat_uuid as prescat_uuid, PRICE_CATEGORY.seatcat_uuid as seatcat_uuid, " +
            "PRICE_CATEGORY.pricecat_title as pricecat_title, PRICE_CATEGORY.pricecat_description as pricecat_description, PRICE_CATEGORY.price as price, " +
            "PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, ROOM.room_type as room_type, ROOM.clean as clean, " +
            "ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, SEAT_CATEGORY.seatcat_description as seatcat_description, SEAT_CATEGORY.seatcat_title as seatcat_title, " +
            "MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, MOVIE.movie_title as movie_title , MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description, BUSY_SEAT.busy as busy, BUSY_SEAT.looked as looked " +
            "FROM TICKET " +
            "JOIN SEAT ON TICKET.seat_uuid = SEAT.seat_uuid " +
            "JOIN PRICE_CATEGORY ON TICKET.pricecat_uuid = PRICE_CATEGORY.pricecat_uuid " +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid " +
            "JOIN PRESENTATION ON PRESENTATION.pres_uuid = TICKET.pres_uuid " +
            "JOIN ROOM ON ROOM.room_uuid = PRESENTATION.room_uuid " +
            "JOIN MOVIE ON MOVIE.movie_uuid = PRESENTATION.movie_uuid " +
            "JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION_CATEGORY.prescat_uuid = PRICE_CATEGORY.prescat_uuid " +
            "JOIN BUSY_SEAT ON BUSY_SEAT.pres_uuid = TICKET.pres_uuid AND BUSY_SEAT.seat_uuid = TICKET.seat_uuid " +
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
            return this.deleteObject(Ticket.class, DELETE, new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Ticket> getAll() {
        return this.getAllObjects(Ticket.class, SELECT_ALL, new TicketMapper());
    }

    public boolean getAllByReservation(Reservation reservation) {
        if (reservation != null) {
            List<Ticket> result = this.getObjectsByMultipleArguments(Ticket.class, SELECT_ALL_BY_RESERVATION, new Object[]{reservation.getUuid()}, new TicketMapper(reservation));
            return result != null;
        }
        return false;
    }

    public boolean deleteAllByReservation(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Ticket.class, DELETE_ALL_BY_RESERVATION, new Object[]{key});
        }
        return false;
    }
}
