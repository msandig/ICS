package de.dhbw.ics.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Seat {

    private String uuid;
    private Integer number;
    private Integer row;
    private SeatCategory seatCategory;
    private Room room;
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
}
