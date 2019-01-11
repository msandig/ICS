package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Movie;
import de.dhbw.ics.vo.Presentation;
import de.dhbw.ics.vo.PresentationCategory;
import de.dhbw.ics.vo.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PresentationMapper implements RowMapper<Presentation> {
    @Override
    public Presentation mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("pres_uuid");
        Movie movie = new MovieMapper().mapRow(resultSet, i);
        Room room = new RoomMapper().mapRow(resultSet, i);
        long date = resultSet.getLong("date");
        PresentationCategory presentationCategory = new PresentationCategoryMapper().mapRow(resultSet, i);
        return new Presentation(uuid, movie, room, date, presentationCategory);
    }
}
