package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class Presentation {

    private String uuid;
    private Movie movie;
    private Room room;
    private long date;
    private PresentationCategory presentationCategory;

    @JsonCreator
    public Presentation(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }

        if (delegate.get("date") instanceof Map) {
            this.date = (long) delegate.get("date");
        }

        if (delegate.get("movie") instanceof Map) {
            this.movie = new Movie((Map<String, Object>) delegate.get("movie"));
        }
        if (delegate.get("room") instanceof Map) {
            this.room = new Room((Map<String, Object>) delegate.get("room"));
        }
        if (delegate.get("presentationCategory") instanceof Map) {
            this.presentationCategory = new PresentationCategory((Map<String, Object>) delegate.get("presentationCategory"));
        }
    }

    public Presentation(String uuid, Movie movie, Room room, long date, PresentationCategory presentationCategory) {
        this.uuid = uuid;
        this.movie = movie;
        this.room = room;
        this.date = date;
        this.presentationCategory = presentationCategory;
    }


    public Presentation(Movie movie, Room room, long date, PresentationCategory presentationCategory) {
        this.movie = movie;
        this.room = room;
        this.date = date;
        this.uuid = UUID.randomUUID().toString();
        this.presentationCategory = presentationCategory;
    }

    public PresentationCategory getPresentationCategory() {
        return presentationCategory;
    }

    public void setPresentationCategory(PresentationCategory presentationCategory) {
        this.presentationCategory = presentationCategory;
    }

    public String getUuid() {
        return uuid;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Presentation that = (Presentation) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(movie, that.movie)
                .append(room, that.room)
                .append(date, that.date)
                .append(presentationCategory, that.presentationCategory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(movie)
                .append(room)
                .append(date)
                .append(presentationCategory)
                .toHashCode();
    }
}
