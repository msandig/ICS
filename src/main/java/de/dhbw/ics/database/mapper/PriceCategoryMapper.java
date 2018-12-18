package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.PresentationCategory;
import de.dhbw.ics.vo.PriceCategory;
import de.dhbw.ics.vo.SeatCategory;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PriceCategoryMapper implements RowMapper<PriceCategory> {
    @Override
    public PriceCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("pricecat_uuid");
        PresentationCategory presentationCategory = new PresentationCategoryMapper().mapRow(resultSet, i);
        SeatCategory seatCategory = new SeatCategoryMapper().mapRow(resultSet, i);
        String title = resultSet.getString("pricecat_title");
        String description = resultSet.getString("pricecat_description");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new PriceCategory(uuid, presentationCategory, seatCategory, title, description, price);
    }
}
