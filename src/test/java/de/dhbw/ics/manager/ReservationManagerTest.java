package de.dhbw.ics.manager;


import de.dhbw.ics.controller.web.ResultMessage;
import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

//Sorts by method name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class ReservationManagerTest {


    @TestConfiguration
    static class ReservationManagerTestContextConfiguration {

        @Bean
        public ReservationManager reservationManager() {
            return new ReservationManager();
        }
    }

    @MockBean
    private ReservationDao reservationDao;

    @MockBean
    private PresentationManager presentationManager;

    @MockBean
    private PresentationDao presentationDao;

    @MockBean
    private SeatDao seatDao;

    @MockBean
    private BusySeatDao busySeatDao;

    @MockBean
    private MovieDao movieDao;

    @MockBean
    private UserDao userDao;

    @MockBean
    private RoleDao roleDao;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private PriceCategoryDao priceCategoryDao;

    @MockBean
    private TicketDao ticketDao;

    @Autowired
    private ReservationManager reservationManager;
    private Reservation reservation;
    private PriceCategory priceCategory;
    private Presentation presentation;
    private Movie movie;
    private Genre genre;
    private Seat seat1;
    private Seat seat2;
    private SeatCategory seatCategory;
    private Room room;
    private User user;
    private Role role;
    private Ticket ticket;
    private PresentationCategory presentationCategory;


    @Before
    public void setUp() {

        this.room = new Room("3D", true, true, 1);
        this.seatCategory = new SeatCategory("test", "test");

        this.seat1 = new Seat(room, seatCategory, 1, 1);
        this.seat2 = new Seat(room, seatCategory, 2, 1);

        this.genre = new Genre("test");
        this.movie = new Movie(2018, "test", "test", genre, 18, 120);

        this.presentationCategory = new PresentationCategory("test", "test");
        this.presentation = new Presentation(movie, room, Calendar.getInstance().getTimeInMillis(), presentationCategory);
        this.priceCategory = new PriceCategory(presentationCategory, seatCategory, "Test", "test", new BigDecimal("10.00"));

        Mockito.when(this.priceCategoryDao.getAll()).thenReturn(Arrays.asList(priceCategory));

        Mockito.when(this.presentationDao.get(presentation.getUuid())).thenReturn(presentation);
        Mockito.when(this.presentationDao.getAll()).thenReturn(Arrays.asList(presentation));
        Mockito.when(this.presentationDao.delete(presentation.getUuid())).thenReturn(true);
        Mockito.when(this.presentationDao.getPresentationsByMovieAndDateInterval(1, 1, movie.getUuid())).thenReturn(Arrays.asList(presentation));
        Mockito.when(this.presentationDao.getPresentationsByTitleAndDateInterval(1, 1, movie.getTitle())).thenReturn(Arrays.asList(presentation));
        Mockito.when(this.presentationDao.getPresentationsBetweenInterval(1, 2)).thenReturn(Arrays.asList(presentation));
        Mockito.when(this.presentationDao.persist(presentation)).thenReturn(true);

        Mockito.when(this.movieDao.getAll()).thenReturn(Arrays.asList(movie));
        Mockito.when(this.movieDao.getMovieByTitle(movie.getTitle())).thenReturn(Arrays.asList(movie));
        Mockito.when(this.movieDao.getMoviesBetweenDate(1, 2)).thenReturn(Arrays.asList(movie));

        Mockito.when(this.seatDao.getAllByRoom(room)).thenReturn(Arrays.asList(seat1, seat2));
        Mockito.when(this.busySeatDao.getAllByPresentation(presentation)).thenReturn(null);

        role = new Role("testRole");
        roleDao.persist(role);
        user = new User("test@gmx.de", "124mgm", role);
        userDao.persist(user);
        reservation = new Reservation(Calendar.getInstance().getTimeInMillis(), false);
        reservation.setUser(user);
        reservation.setNumber(42);

        Mockito.when(this.userDao.persist(user)).thenReturn(true);
        Mockito.when(this.userDao.get(user.getEmail())).thenReturn(user);

        Mockito.when(this.reservationDao.getByNumber(reservation.getNumber())).thenReturn(reservation);
        Mockito.when(this.reservationDao.delete(reservation.getUuid())).thenReturn(true);
    }

    @Test
    public void test1persistUser() {
        assertNotEquals(reservationManager.persistUser(user).getClass(), ResultMessage.class);
    }
    @Test
    public void test2persistReservation(){
        reservationManager.persistReservation(reservation, "239d2938dh392d93");
    }
    @Test
    public void test3getUser() {
        User testUser = (User) reservationManager.getUser(user.getEmail(), user.getPassword());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getPassword(), user.getPassword());
    }
    @Test
    public void test4getReservation() {
        Reservation testReservation = (Reservation) reservationManager.getReservation(reservation.getUser().getEmail(), reservation.getNumber());
        assertEquals(testReservation.getTickets(), reservation.getTickets());
        assertEquals(testReservation.getDate(), reservation.getDate());
        assertEquals(testReservation.getNumber(), reservation.getNumber());
        assertEquals(testReservation.getUser(), reservation.getUser());
        assertEquals(testReservation.getUuid(), reservation.getUuid());
    }
    @Test
    public void test5lockSeats(){
        reservationManager.lockSeats(presentation.getUuid(), List.of(seat1, seat2), "239d2938dh392d93", true);

    }
    @Test
    public void test6deleteReservation(){
        assertEquals(reservationManager.deleteReservation(reservation.getUser().getEmail(), reservation.getNumber()), ResultMessage.SUCCESS);
    }

}
