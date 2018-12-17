package de.dhbw.ics.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {

    private String uuid;
    private String roomType;
    private boolean isClean;
    private boolean isVIP;
    private List<Seat> seats = new ArrayList<Seat>();

    public Room(String uuid, String roomType, boolean isClean, boolean isVIP) {
        this.uuid = uuid;
        this.roomType = roomType;
        this.isClean = isClean;
        this.isVIP = isVIP;
    }

    public Room(String roomType, boolean isClean, boolean isVIP) {
        this.roomType = roomType;
        this.isClean = isClean;
        this.isVIP = isVIP;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
