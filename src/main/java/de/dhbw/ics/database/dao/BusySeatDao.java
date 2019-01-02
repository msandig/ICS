package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.BusySeat;

import java.util.List;

public class BusySeatDao extends AbstractDao<BusySeat> {

    private static final String PERSIST = "INSERT INTO BUSY_SEAT (seat_uuid, res_uuid, busy) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM BUSY_SEAT WHERE res_uuid = ?";
    private static final String DELETE = "DELETE FROM BUSY_SEAT WHERE res_uuid = ?";
    private static final String UPDATE = "UPDATE BUSY_SEAT SET busy = ? WHERE res_uuid = ? AND seat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM BUSY_SEAT WHERE res_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM BUSY_SEAT";

    @Override
    public boolean persist(BusySeat object) {
        return false;
    }

    @Override
    public BusySeat get(Object key) {
        return null;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }

    @Override
    public List<BusySeat> getAll() {
        return null;
    }
}
