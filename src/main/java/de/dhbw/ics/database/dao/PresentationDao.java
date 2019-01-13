package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.PresentationMapper;
import de.dhbw.ics.vo.Presentation;

import java.util.List;

public class PresentationDao extends AbstractDao<Presentation> {

    private static final String PERSIST = "INSERT INTO PRESENTATION (pres_uuid, movie_uuid, prescat_uuid, room_uuid, date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT PRESENTATION.pres_uuid as pres_uuid, PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.prescat_uuid as prescat_uuid, " +
            "PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, ROOM.room_type as room_type, ROOM.clean as clean, ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, " +
            "PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description " +
            "FROM PRESENTATION " +
            "JOIN MOVIE ON PRESENTATION.movie_uuid = MOVIE.movie_uuid " +
            "JOIN GENRE ON MOVIE.genre_uuid = GENRE.genre_uuid " +
            "JOIN ROOM ON PRESENTATION.room_uuid = ROOM.room_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION.prescat_uuid = PRESENTATION_CATEGORY.prescat_uuid " +
            "WHERE pres_uuid = ?";
    private static final String DELETE = "DELETE FROM PRESENTATION WHERE pres_uuid = ?";
    private static final String UPDATE = "UPDATE PRESENTATION SET movie_uuid = ?, prescat_uuid = ?, room_uuid = ?, date = ? WHERE pres_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM PRESENTATION WHERE pres_uuid = ?";
    private static final String SELECT_ALL = "SELECT PRESENTATION.pres_uuid as pres_uuid, PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.prescat_uuid as prescat_uuid, " +
            "PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, ROOM.room_type as room_type, ROOM.clean as clean, ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, " +
            "PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description " +
            "FROM PRESENTATION " +
            "JOIN MOVIE ON PRESENTATION.movie_uuid = MOVIE.movie_uuid " +
            "JOIN GENRE ON MOVIE.genre_uuid = GENRE.genre_uuid " +
            "JOIN ROOM ON PRESENTATION.room_uuid = ROOM.room_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION.prescat_uuid = PRESENTATION_CATEGORY.prescat_uuid LIMIT 1000";

    private static final String SELECT_ALL_BETWEEN_DATE = "SELECT PRESENTATION.pres_uuid as pres_uuid, PRESENTATION.movie_uuid as movie_uuid, PRESENTATION.prescat_uuid as prescat_uuid, " +
            "PRESENTATION.room_uuid as room_uuid, PRESENTATION.date as date, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name, ROOM.room_type as room_type, ROOM.clean as clean, ROOM.vip_seats as vip_seats, ROOM.room_number as room_number, " +
            "PRESENTATION_CATEGORY.prescat_title as prescat_title, PRESENTATION_CATEGORY.prescat_description as prescat_description " +
            "FROM PRESENTATION " +
            "JOIN MOVIE ON PRESENTATION.movie_uuid = MOVIE.movie_uuid " +
            "JOIN GENRE ON MOVIE.genre_uuid = GENRE.genre_uuid " +
            "JOIN ROOM ON PRESENTATION.room_uuid = ROOM.room_uuid " +
            "JOIN PRESENTATION_CATEGORY ON PRESENTATION.prescat_uuid = PRESENTATION_CATEGORY.prescat_uuid WHERE PRESENTATION.date BETWEEN ? AND ?";

    @Override
    public boolean persist(Presentation object) {
        if (object != null) {
            return this.persistObject(Presentation.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getMovie().getUuid(), object.getPresentationCategory().getUuid(), object.getRoom().getUuid(), object.getDate());
        }
        return false;
    }

    @Override
    public Presentation get(Object key) {
        if (key != null) {
            return this.getObject(Presentation.class, SELECT, new Object[]{key}, new PresentationMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Presentation.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Presentation> getAll() {
        return this.getAllObjects(Presentation.class, SELECT_ALL, new PresentationMapper());
    }

    public List<Presentation> getPresentationsBetweenIntervall(long startDate, long endDate) {
        if (startDate != 0 && endDate != 0) {
            return this.getObjectsByMultipleArguments(Presentation.class, SELECT_ALL_BETWEEN_DATE, new Object[]{startDate, endDate}, new PresentationMapper());
        }
        return null;
    }
}
