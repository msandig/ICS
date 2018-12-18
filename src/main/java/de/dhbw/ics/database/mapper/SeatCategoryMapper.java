package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.SeatCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatCategoryMapper implements RowMapper<SeatCategory> {
    @Override
    public SeatCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("seatcat_uuid");
        String title = resultSet.getString("seatcat_uuid");
        String description = resultSet.getString("seatcat_title");
        return new SeatCategory(uuid, title, description);
    }
}
