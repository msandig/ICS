package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.BusySeatMapper;
import de.dhbw.ics.vo.BusySeat;
import de.dhbw.ics.vo.Presentation;
import de.dhbw.ics.vo.Seat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BusySeatDao extends AbstractDao<BusySeat> {

    private static final String PERSIST = "MERGE INTO BUSY_SEAT (seat_uuid, pres_uuid, busy, locked, timestamp, sessionID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM BUSY_SEAT WHERE pres_uuid = ? AND seat_uuid = ?";
    private static final String DELETE = "DELETE FROM BUSY_SEAT WHERE pres_uuid = ? AND seat_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM BUSY_SEAT";
    private static final String SELECT_ALL_BY_PRESENTATION = "SELECT * FROM BUSY_SEAT WHERE pres_uuid = ?";

    private static final Logger LOG = LoggerFactory.getLogger(BusySeatDao.class);

    public boolean persistBatch(List<BusySeat> busySeats) {
        if (busySeats != null && busySeats.size() != 0) {
            List<Object[]> objects = new ArrayList<>();
            for(BusySeat bs : busySeats){
                objects.add(new Object[]{bs.getSeat().getUuid(), bs.getPresentation().getUuid(), bs.isBusy(), bs.isLooked(), bs.getTimestamp(), bs.getSessionID()});
            }
            try {
                if (this.jdbcTemplate != null) {
                    this.jdbcTemplate.batchUpdate(PERSIST, objects);
                    LOG.info("Persist / Update {} BusySeat(s)!", objects.size());
                    return true;
                }
            } catch (DataAccessException e) {
                LOG.error("Could not persist BusySeat!");
                LOG.error(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean persist(BusySeat object) {
        if (object != null) {
            try {
                if (this.jdbcTemplate != null) {
                    this.jdbcTemplate.update(PERSIST, object.getSeat().getUuid(), object.getPresentation().getUuid(), object.isBusy(), object.isLooked(), object.getTimestamp(), object.getSessionID());
                    LOG.info("Persist / Update BusySeat!");
                    return true;
                }
            } catch (DataAccessException e) {
                LOG.error("Could not persist BusySeat!");
                LOG.error(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public BusySeat get(Object map) {
        if (map instanceof Map) {
            Map m = (Map<String, Object>) map;
            Presentation p = (Presentation) m.get("p");
            Seat s = (Seat) m.get("s");
            return this.getObject(BusySeat.class, SELECT, new Object[]{p.getUuid(), s.getUuid()}, new BusySeatMapper(p, s));
        }
        return null;
    }

    @Override
    public boolean delete(Object map) {
        if (map instanceof Map) {
            Map m = (Map<String, Object>) map;
            Presentation p = (Presentation) m.get("p");
            Seat s = (Seat) m.get("s");
            return this.deleteObject(BusySeat.class, DELETE, new Object[]{p.getUuid(), s.getUuid()});
        }
        return false;
    }

    @Override
    public List<BusySeat> getAll() {
        return this.getAllObjects(BusySeat.class, SELECT_ALL, new BusySeatMapper());
    }

    public List<BusySeat> getAllByPresentation(Presentation presentation) {
        if (presentation != null && presentation.getRoom() != null && presentation.getRoom().getSeats().keySet().size() != 0) {
            return this.getObjectsByMultipleArguments(BusySeat.class, SELECT_ALL_BY_PRESENTATION, new Object[]{presentation.getUuid()}, new BusySeatMapper(presentation));
        }
        return null;
    }
}