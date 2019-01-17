package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Reservation {

    private String uuid;
    private Integer number;
    private long date;
    private User user = null;
    private boolean payed;
    private List<Ticket> tickets = new ArrayList<>();

    @JsonCreator
    public Reservation(Map<String, Object> delegate) {
        if (delegate.get("uuid") != null) {
            this.uuid = (String) delegate.get("uuid");
        } else {
            this.uuid = UUID.randomUUID().toString();
        }
        this.date = (long) delegate.get("date");

        if (delegate.get("user") instanceof Map) {
            this.user = new User((Map<String, Object>) delegate.get("user"));
        }

        this.payed = Boolean.parseBoolean((String) delegate.get("payed"));
        if (delegate.get("tickets") instanceof Map) {
            List<Map<String, Object>> unmappedTickets = (List<Map<String, Object>>) delegate.get("tickets");
            for (Map<String, Object> unmappedTicket : unmappedTickets) {
                Ticket ticket = new Ticket(unmappedTicket);
                ticket.setReservation(this);
                this.tickets.add(ticket);
            }
        }
    }

    public Reservation(String uuid, Integer number, long date, boolean payed) {
        this.uuid = uuid;
        this.number = number;
        this.date = date;
        this.payed = payed;
    }

    public Reservation(long date, boolean payed) {

        this.date = date;
        this.payed = payed;
        this.uuid = UUID.randomUUID().toString();
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUuid() {
        return uuid;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        return new EqualsBuilder()
                .append(payed, that.payed)
                .append(uuid, that.uuid)
                .append(date, that.date)
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(date)
                .append(user)
                .append(payed)
                .toHashCode();
    }
}
