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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketDaoTest {
    private static Ticket ticket;
    private static Reservation reservation;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private SeatCategoryDao seatCategoryDao;

    @Autowired
    private PriceCategoryDao priceCategoryDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    @Autowired
    private GenreDao genereDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {

    }

    @Test
    public void test1Persist() {
        PresentationCategory presentationCategory = new PresentationCategory("normal","3D Vorstellung");
        SeatCategory seatCategory = new SeatCategory("normal","Ein normaler Sitz");
        PriceCategory priceCategory = new PriceCategory(presentationCategory,seatCategory,"Der große Filmeabend","Ein Abend voller Filme",  new BigDecimal("12.34"));

        presentationCategoryDao.persist(presentationCategory);
        seatCategoryDao.persist(seatCategory);
        priceCategoryDao.persist(priceCategory);


        Room room = new Room("Mittleres 3D Kino", true, false,2);
        Seat seat = new Seat(room,seatCategory,12,3);

        roomDao.persist(room);
        seatDao.persist(seat);

        long date = 12122018;
        Movie movie = new Movie(2018,"Der Hobbit","Ein Film",18,232);
        Genre genre = new Genre("Drama");
        genereDao.persist(genre);
        movie.setGenre(genre);

        movieDao.persist(movie);

        Presentation presentation = new Presentation(movie,room,date,presentationCategory);
        Role role = new Role("Gast");
        roleDao.persist(role);
        User user = new User("computerfreak@freakylikeme.com","freakyBoy38",role);
        userDao.persist(user);
        reservation = new Reservation(12122018,true);
        reservation.setUser(user);

        presentationDao.persist(presentation);
        reservationDao.persist(reservation);

        ticket = new Ticket(seat,priceCategory,presentation);
        ticket.setReservation(reservation);


        ticketDao.persist(ticket);
    }

    @Test
    public void test2Get() {
        ticketDao.get(ticket.getUuid());
    }

    @Test
    public void test3GetAll(){
        List<Ticket> listTicket = ticketDao.getAll();
        System.out.print(212);
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
