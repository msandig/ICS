package de.dhbw.ics.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {

    private Integer uuid = 0;
    private Date date = null;
    private User user = null;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Reservation(Integer uuid, Date date) {
        this.uuid = uuid;
        this.date = date;
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
