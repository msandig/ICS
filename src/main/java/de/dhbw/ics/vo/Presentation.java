package de.dhbw.ics.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.UUID;

public class Presentation {

    private String uuid;
    private Movie movie;
    private Room room;
    private Date date;
    private PresentationCategory presentationCategory;

    public Presentation(String uuid, Movie movie, Room room, Date date, PresentationCategory presentationCategory) {
        this.uuid = uuid;
        this.movie = movie;
        this.room = room;
        this.date = date;
        this.presentationCategory = presentationCategory;
    }

    public Presentation(Movie movie, Room room, Date date, PresentationCategory presentationCategory) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
