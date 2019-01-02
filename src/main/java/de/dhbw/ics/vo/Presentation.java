package de.dhbw.ics.vo;

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
}
