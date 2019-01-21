package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.BusySeat;
import de.dhbw.ics.vo.Presentation;
import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusySeatMapper implements RowMapper<BusySeat> {

    private Presentation presentation;
    private Room room;
    private Seat seat;

    public BusySeatMapper() {

    }

    public BusySeatMapper(Presentation presentation) {
        this.presentation = presentation;
        this.room = presentation.getRoom();
    }

    public BusySeatMapper(Presentation presentation, Seat seat) {
        this.presentation = presentation;
        this.room = presentation.getRoom();
        this.seat = seat;
    }

    @Override
    public BusySeat mapRow(ResultSet resultSet, int i) throws SQLException {
        String seatUUID = resultSet.getString("seat_uuid");
        String presentationUUID = resultSet.getString("pres_uuid");
        boolean locked = resultSet.getBoolean("locked");
        boolean busy = resultSet.getBoolean("busy");
        String sessionID = resultSet.getString("sessionID");
        long timestamp = resultSet.getLong("timestamp");
        BusySeat busySeat = new BusySeat();
        busySeat.setBusy(busy);
        busySeat.setLocked(locked);
        busySeat.setSessionID(sessionID);
        busySeat.setTimestamp(timestamp);

        if (this.room != null && this.room.getSeats().size() != 0 && this.presentation != null && this.presentation.getUuid().equals(presentationUUID)) {
            busySeat.setPresentation(this.presentation);
            if(this.seat == null) {
                Seat s = this.room.getSeats().get(seatUUID);
                busySeat.setSeat(s);
                s.addBusy(busySeat);
                s.setCurrentBusySeat(busySeat);
            }else{
                busySeat.setSeat(this.seat);
                this.seat.addBusy(busySeat);
            }
        }else{
            Seat s = new Seat(seatUUID, null, null, 0, 0 );
            busySeat.setSeat(s);
            busySeat.setPresentation(new Presentation(presentationUUID, null,null, 0, null));
            s.addBusy(busySeat);
        }
        return busySeat;
    }
}
