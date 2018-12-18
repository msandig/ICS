package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.PresentationCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PresentationCategoryMapper implements RowMapper<PresentationCategory> {
    @Override
    public PresentationCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("prescat_uuid");
        String title = resultSet.getString("prescat_title");
        String description = resultSet.getString("prescat_description");
        return new PresentationCategory(uuid, title, description);
    }
}
