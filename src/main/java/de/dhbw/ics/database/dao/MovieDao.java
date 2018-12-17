package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.MovieMapper;
import de.dhbw.ics.vo.Movie;

import java.util.Date;
import java.util.List;

public class MovieDao extends AbstractDao<Movie> {

    private static final String PERSIST = "INSERT INTO MOVIE (movie_uuid, genre_uuid, prod_year, title, description, fsk, runtime, picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM MOVIE JOIN GENRE ON MOVIE.movie_uuid = GENRE.uuid WHERE movie_uuid = ?";
    private static final String DELETE = "DELETE FROM MOVIE WHERE movie_uuid = ?";
    private static final String UPDATE = "UPDATE MOVIE SET genre_uuid = ?, prod_year = ?, title = ?, description = ?, fsk = ?, runtime = ?, picture = ? WHERE movie_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM MOVIE WHERE movie_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM MOVIE";
    private static final String SELECT_BETWEEN_DATE = "SELECT * FROM MOVIE JOIN GENRE ON MOVIE.movie_uuid = GENRE.uuid WHERE data BETWEEN ? AND ?";


    @Override
    public boolean persist(Movie object) {
        if(object instanceof Movie && object != null) {
            Movie movie = object;
            return this.persistObject(Movie.class, movie.getUuid(), COUNT, UPDATE, PERSIST, movie.getUuid(), movie.getGenre().getUuid(), movie.getProductionYear(), movie.getTitle(), movie.getDescription(), movie.getFsk(), movie.getRunTime(), movie.getPicture());
        }
        return false;
    }

    @Override
    public Movie get(Object key) {
        return this.getObject(Movie.class, SELECT, new Object[]{key}, new MovieMapper());
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(Movie.class, DELETE, key);
    }

    @Override
    public List<Movie> getAll() {
        return this.getAllObjects(Movie.class, SELECT_ALL, new MovieMapper());
    }

    public List<Movie> getMoviesBetweenIntervall(Date startDate, Date endDate){
        return this.getObjectsByMultipleArguments(Movie.class, SELECT_BETWEEN_DATE, new Object[]{startDate, endDate}, new MovieMapper());
    }
}
