package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Room {

    private String uuid;
    private String roomType;
    private boolean isClean;
    private boolean isVIP;
    private Integer number;
    private Map<String, Seat> seats = new HashMap<>();

    @JsonCreator
    public Room(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }
        this.roomType = (String) delegate.get("roomType");
        this.isVIP =  Boolean.parseBoolean((String) delegate.get("isVIP"));
        this.isClean =  Boolean.parseBoolean((String) delegate.get("isClean"));
        this.number =  (Integer) delegate.get("number");
    }

    public Room(String uuid, String roomType, boolean isClean, boolean isVIP, int number) {
        this.uuid = uuid;
        this.roomType = roomType;
        this.isClean = isClean;
        this.isVIP = isVIP;
        this.number = number;
    }


    public Room(String roomType, boolean isClean, boolean isVIP, int number) {
        this.roomType = roomType;
        this.isClean = isClean;
        this.isVIP = isVIP;
        this.uuid = UUID.randomUUID().toString();
        this.number = number;
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

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public void setSeats(Map<String, Seat> seats) {
        this.seats = seats;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return new EqualsBuilder()
                .append(isClean, room.isClean)
                .append(isVIP, room.isVIP)
                .append(uuid, room.uuid)
                .append(roomType, room.roomType)
                .append(number, room.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(roomType)
                .append(isClean)
                .append(isVIP)
                .append(number)
                .toHashCode();
    }
}
