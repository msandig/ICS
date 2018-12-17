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
        if(object instanceof SeatCategory && object != null) {
            SeatCategory seatCategory = object;
            return this.persistObject(SeatCategory.class, seatCategory.getUuid(), COUNT, UPDATE, PERSIST, seatCategory.getUuid(), seatCategory.getTitle(), seatCategory.getDescription());
        }
        return false;
    }

    @Override
    public SeatCategory get(Object key) {
        return this.getObject(SeatCategory.class, SELECT, new Object[]{key}, new SeatCategoryMapper());
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(SeatCategory.class, DELETE, key);
    }

    @Override
    public List<SeatCategory> getAll() {
        return this.getAllObjects(SeatCategory.class, SELECT_ALL, new SeatCategoryMapper());
    }
}
