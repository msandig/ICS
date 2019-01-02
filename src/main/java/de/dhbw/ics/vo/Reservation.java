package de.dhbw.ics.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {

    private Integer uuid ;
    private Date date;
    private User user = null;
    private boolean payed;
    private List<Ticket> tickets = new ArrayList<>();

    public Reservation(Integer uuid, Date date, boolean payed) {
        this.uuid = uuid;
        this.date = date;
        this.payed = payed;
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

    public Integer getUuid() {
        return uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
