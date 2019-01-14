package de.dhbw.ics.database.dao;


import de.dhbw.ics.vo.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketDaoTest {
    private static Ticket ticket;
    private static Reservation reservation;

    @Autowired
    private TicketDao ticketDao;

    @BeforeClass
    public static void setUp() throws Exception {
        PresentationCategory presentationCategory = new PresentationCategory("normal","3D Vorstellung");
        SeatCategory seatCategory = new SeatCategory("normal","Ein normaler Sitz");
        PriceCategory priceCategory = new PriceCategory(presentationCategory,seatCategory,"Der große Filmeabend","Ein Abend voller Filme",  new BigDecimal("12.34"));

        Room room = new Room("Mittleres 3D Kino", true, false,2);
        Seat seat = new Seat(room,seatCategory,12,3);

        long date = 12122018;
        Movie movie = new Movie(2018,"Der Hobbit","Ein Film",18,232);
        Presentation presentation = new Presentation(movie,room,date,presentationCategory);

        reservation = new Reservation(12,12122018,true);

        ticket = new Ticket(seat,priceCategory,presentation);
        ticket.setReservation(reservation);

        System.out.println(ticket);
    }

    @Test
    public void test1Persist() {
        ticketDao.persist(ticket);
    }

    @Test
    public void test2Get() {
        ticketDao.get(ticket.getUuid());
    }

    @Test
    public void test3GetAll(){
        ticketDao.getAll();
    }

    @Test
    public void test4GetAllByReservation(){
        ticketDao.getAllByReservation(reservation);
    }

    @Test
    public void test4Delete(){
        ticketDao.delete(ticket.getUuid());
    }

    @Test
    public void test5DeleteAllByReservation(){
        ticketDao.deleteAllByReservation(reservation);
    }
}
