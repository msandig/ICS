package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Genre(resultSet.getString("genre_uuid"), resultSet.getString("name"));
    }
}
