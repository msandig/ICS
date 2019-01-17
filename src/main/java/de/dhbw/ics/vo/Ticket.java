package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class Ticket {

    private String uuid;

    @JsonIgnore
    private Reservation reservation = null;
    private Seat seat;
    private PriceCategory priceCategory;
    private Presentation presentation;

    @JsonCreator
    public Ticket(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }
        if (delegate.get("presentation") instanceof Map) {
            this.presentation = new Presentation((Map<String, Object>) delegate.get("presentation"));
        }

        if (delegate.get("priceCategory") instanceof Map) {
            this.priceCategory = new PriceCategory((Map<String, Object>) delegate.get("priceCategory"));
        }

        if (delegate.get("seat") instanceof Map) {
            this.seat = new Seat((Map<String, Object>) delegate.get("seat"));
        }
    }

    public Ticket(String uuid, Seat seat, PriceCategory priceCategory, Presentation presentation) {
        this.uuid = uuid;
        this.seat = seat;
        this.priceCategory = priceCategory;
        this.presentation = presentation;
    }

    public Ticket(Seat seat, PriceCategory priceCategory, Presentation presentation) {
        this.seat = seat;
        this.priceCategory = priceCategory;
        this.presentation = presentation;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(uuid, ticket.uuid)
                .append(reservation, ticket.reservation)
                .append(seat, ticket.seat)
                .append(priceCategory, ticket.priceCategory)
                .append(presentation, ticket.presentation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(reservation)
                .append(seat)
                .append(priceCategory)
                .append(presentation)
                .toHashCode();
    }
}
