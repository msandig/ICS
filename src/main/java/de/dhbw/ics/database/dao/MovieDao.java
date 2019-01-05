package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.MovieMapper;
import de.dhbw.ics.vo.Movie;

import java.util.Calendar;
import java.util.List;

public class MovieDao extends AbstractDao<Movie> {

    private static final String PERSIST = "INSERT INTO MOVIE (movie_uuid, genre_uuid, prod_year, movie_title, movie_description, fsk, runtime, picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT MOVIE.movie_uuid as movie_uuid, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name FROM MOVIE JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid WHERE movie_uuid = ?";
    private static final String DELETE = "DELETE FROM MOVIE WHERE movie_uuid = ?";
    private static final String UPDATE = "UPDATE MOVIE SET genre_uuid = ?, prod_year = ?, movie_title = ?, movie_description = ?, fsk = ?, runtime = ?, picture = ? WHERE movie_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM MOVIE WHERE movie_uuid = ?";
    private static final String SELECT_ALL = "SELECT MOVIE.movie_uuid as movie_uuid, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name FROM MOVIE JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid";
    private static final String SELECT_BETWEEN_DATE = "SELECT MOVIE.movie_uuid as movie_uuid, MOVIE.genre_uuid as genre_uuid, MOVIE.prod_year as prod_year, " +
            "MOVIE.movie_title as movie_title, MOVIE.movie_description as movie_description, MOVIE.fsk as fsk, MOVIE.runtime as runtime, MOVIE.picture as picture, " +
            "GENRE.name as name FROM MOVIE JOIN GENRE ON GENRE.genre_uuid = MOVIE.genre_uuid WHERE MOVIE.prod_year BETWEEN ? AND ?";


    @Override
    public boolean persist(Movie object) {
        if (object != null) {
            return this.persistObject(Movie.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getGenre().getUuid(), object.getProductionYear(), object.getTitle(), object.getDescription(), object.getFsk(), object.getRunTime(), object.getPicture());
        }
        return false;
    }

    @Override
    public Movie get(Object key) {

        if (key != null && !key.equals("")) {
            return this.getObject(Movie.class, SELECT, new Object[]{key}, new MovieMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Movie.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Movie> getAll() {
        return this.getAllObjects(Movie.class, SELECT_ALL, new MovieMapper());
    }

    public List<Movie> getMoviesBetweenIntervall(int startDate, int endDate) {
        if (startDate != 0 && endDate != 0 && startDate >= 1900 && endDate <= Calendar.getInstance().get(Calendar.YEAR) + 1) {
            return this.getObjectsByMultipleArguments(Movie.class, SELECT_BETWEEN_DATE, new Object[]{startDate, endDate}, new MovieMapper());
        }
        return null;
    }
}
