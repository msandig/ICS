package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.PriceCategoryMapper;
import de.dhbw.ics.vo.PriceCategory;

import java.util.List;

public class PriceCategoryDao extends AbstractDao<PriceCategory> {
    private static final String PERSIST = "INSERT INTO PRICE_CATEGORY (pricecat_uuid, prescat_uuid, seatcat_uuid, pricecat_title, pricecat_description, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PRICE_CATEGORY " +
            "JOIN PRESENTATION_CATEGORY ON PRICE_CATEGORY.prescat_uuid = PRESENTATION_CATEGORY.prescat_uuid " +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid " +
            "WHERE pricecat_uuid = ?";
    private static final String DELETE = "DELETE FROM PRICE_CATEGORY WHERE pres_uuid = ?";
    private static final String UPDATE = "UPDATE PRICE_CATEGORY SET prescat_uuid = ?, seatcat_uuid = ?, pricecat_title = ?, pricecat_description = ?, price = ? WHERE pricecat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM PRICE_CATEGORY WHERE pricecat_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM PRICE_CATEGORY" +
            "JOIN PRESENTATION_CATEGORY ON PRICE_CATEGORY.prescat_uuid = PRESENTATION_CATEGORY.prescat_uuid " +
            "JOIN SEAT_CATEGORY ON PRICE_CATEGORY.seatcat_uuid = SEAT_CATEGORY.seatcat_uuid ";

    @Override
    public boolean persist(PriceCategory object) {
        if (object != null) {
            return this.persistObject(PriceCategory.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getPresentationCategory().getUuid(), object.getSeatCategory().getUuid(), object.getTitle(), object.getDescription(), object.getPrice());
        }
        return false;
    }

    @Override
    public PriceCategory get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(PriceCategory.class, SELECT, new Object[]{key}, new PriceCategoryMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(PriceCategory.class, DELETE, key);
        }
        return false;
    }

    @Override
    public List<PriceCategory> getAll() {
        return this.getAllObjects(PriceCategory.class, SELECT_ALL, new PriceCategoryMapper());
    }
}
