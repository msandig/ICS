package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class BusySeat {

    private boolean isBusy = false;

    private Seat seat = null;

    private Presentation presentation = null;
    private boolean locked = false;

    @JsonIgnore
    private String sessionID = StringUtils.EMPTY;

    @JsonIgnore
    private long timestamp = 0;

    public BusySeat(){}

    public BusySeat(boolean isBusy, Seat seat, Presentation presentation, boolean locked, String sessionID, long timestamp) {
        this.isBusy = isBusy;
        this.seat = seat;
        this.presentation = presentation;
        this.locked = locked;
        this.sessionID = sessionID;
        this.timestamp = timestamp;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonGetter("presentation")
    public String getPresentationUUID() {
        if (this.presentation != null) {
            return this.presentation.getUuid();
        }
        return null;
    }

    @JsonGetter("seat")
    public String getSeatUUID() {
        if (this.seat != null) {
            return this.seat.getUuid();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BusySeat busySeat = (BusySeat) o;

        return new EqualsBuilder()
                .append(isBusy, busySeat.isBusy)
                .append(locked, busySeat.locked)
                .append(seat, busySeat.seat)
                .append(presentation, busySeat.presentation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isBusy)
                .append(seat)
                .append(presentation)
                .append(locked)
                .toHashCode();
    }

    public static int compareLockTimestamp(BusySeat bs) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(bs.getTimestamp());
        c1.add(Calendar.MINUTE, 5);
        return c1.compareTo(c2);
    }
}
