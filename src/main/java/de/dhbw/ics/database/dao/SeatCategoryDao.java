package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.SeatCategoryMapper;
import de.dhbw.ics.vo.SeatCategory;

import java.util.List;

public class SeatCategoryDao extends AbstractDao<SeatCategory> {

    private static final String PERSIST = "INSERT INTO SEAT_CATEGORY(seatcat_uuid, seatcat_title, seatcat_description) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM SEAT_CATEGORY WHERE seatcat_uuid = ?";
    private static final String DELETE = "DELETE FROM SEAT_CATEGORY WHERE seatcat_uuid = ?";
    private static final String UPDATE = "UPDATE SEAT_CATEGORY SET seatcat_title = ?, seatcat_description = ? WHERE seatcat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM SEAT_CATEGORY WHERE seatcat_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM SEAT_CATEGORY";

    @Override
    public boolean persist(SeatCategory object) {
        if (object != null) {
            return this.persistObject(SeatCategory.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getTitle(), object.getDescription());
        }
        return false;
    }

    @Override
    public SeatCategory get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(SeatCategory.class, SELECT, new Object[]{key}, new SeatCategoryMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(SeatCategory.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<SeatCategory> getAll() {
        return this.getAllObjects(SeatCategory.class, SELECT_ALL, new SeatCategoryMapper());
    }
}
