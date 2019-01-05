package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {

        int fsk = resultSet.getInt("fsk");
        Blob picture = resultSet.getBlob("picture");
        int productionYear = resultSet.getInt("prod_year");
        int runtime = resultSet.getInt("runtime");
        String title = resultSet.getString("movie_title");
        String uuid = resultSet.getString("movie_uuid");
        String description = resultSet.getString("movie_description");
        Genre genre = new GenreMapper().mapRow(resultSet, i);
        Movie movie = new Movie(uuid, productionYear, title, description, fsk, runtime);
        movie.setGenre(genre);
        movie.setPicture(picture);
        return movie;
    }
}
