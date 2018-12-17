package de.dhbw.ics.vo;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Seat {

    private String uuid = StringUtils.EMPTY;
    private Integer number = 0;
    private Integer row = 0;
    private SeatCategory seatCategory = null;
    private Room room = null;

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
}
