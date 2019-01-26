package de.dhbw.ics.manager;


import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

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
    private PriceCategoryDao priceCategoryDao;

    @Autowired
    private ReservationManager reservationManager;
    private PresentationManager presentationManager;
    private PriceCategory priceCategory;
    private Presentation presentation;
    private Movie movie;
    private Genre genre;
    private Seat seat1;
    private Seat seat2;
    private SeatCategory seatCategory;
    private Room room;
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


    }

    @Test
    public void test1persistUser() {}
    @Test
    public void test2persistReservation(){}
    @Test
    public void test3getUser() {}
    @Test
    public void test4getReservation() {}
    @Test
    public void test5lockSeats(){}
    @Test
    public void test6deleteReservation(){}

}
