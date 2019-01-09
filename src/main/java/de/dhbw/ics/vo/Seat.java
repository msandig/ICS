package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Seat {

    private String uuid;
    private Integer number;
    private Integer row;
    private SeatCategory seatCategory;

    @JsonIgnore
    private Room room;

    @JsonIgnore
    private Map<String, BusySeat> seatBusy = new HashMap<>();

    public Seat(String uuid, Room room, SeatCategory seatCategory, Integer number, Integer row) {
        this.uuid = uuid;
        this.seatCategory = seatCategory;
        this.room = room;
        this.row = row;
        this.number = number;
    }

    public Seat(Room room, SeatCategory seatCategory, Integer number, Integer row) {
        this.room = room;
        this.seatCategory = seatCategory;
        this.uuid = UUID.randomUUID().toString();
        this.row = row;
        this.number = number;
    }

    public void addBusy(BusySeat busySeat) {
        seatBusy.putIfAbsent(busySeat.getPresentation().getUuid(), busySeat);
    }

    public boolean isBusy(Presentation presentation) {
        return this.seatBusy.get(presentation.getUuid()).isBusy();
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        return new EqualsBuilder()
                .append(uuid, seat.uuid)
                .append(number, seat.number)
                .append(row, seat.row)
                .append(seatCategory, seat.seatCategory)
                .append(room, seat.room)
                .append(seatBusy, seat.seatBusy)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(number)
                .append(row)
                .append(seatCategory)
                .append(room)
                .append(seatBusy)
                .toHashCode();
    }
}
