package de.dhbw.ics.manager;


import de.dhbw.ics.controller.web.ResultMessage;
import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
    private PresentationDao presentationDao;

    @MockBean
    private SeatDao seatDao;

    @MockBean
    private BusySeatDao busySeatDao;

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
    private User user2;
    private Role role;
    private Role role2;
    private Ticket ticket;
    private PresentationCategory presentationCategory;
    private BusySeat busySeat1;
    private BusySeat busySeat2;


    @Before
    public void setUp() {
        this.role = new Role("Guest");
        this.user = new User("test@gmx.de", null, role);
        this.user.setFirstName("Max");
        this.user.setLastName("Mustermann");

        this.role2 = new Role("User");
        this.user2 = new User("test22@gmx.de", "124mgm", role2);
        this.user2.setFirstName("Max");
        this.user2.setLastName("Mustermann");
        Mockito.when(this.userDao.persist(user)).thenReturn(true);
        Mockito.when(this.userDao.get(user.getEmail())).thenReturn(user);
        Mockito.when(this.userDao.get(user2.getEmail())).thenReturn(user2);
        Mockito.when(this.userDao.get("test@gmail.com")).thenReturn(null);
        Mockito.when(this.reservationDao.getAllByUser(user2)).thenReturn(null);


        this.reservation = new Reservation(Calendar.getInstance().getTimeInMillis(), false);
        this.reservation.setUser(user);
        this.reservation.setNumber(100);
        Mockito.when(this.reservationDao.getByNumber(reservation.getNumber())).thenReturn(reservation);
        Reservation res = new Reservation(this.reservation);
        res.setUser(this.user2);
        Mockito.when(this.reservationDao.getByNumber(99)).thenReturn(res);

        this.room = new Room("3D", true, true, 1);
        this.seatCategory = new SeatCategory("test", "test");

        this.seat1 = new Seat(this.room, this.seatCategory, 1, 1);
        this.seat2 = new Seat(this.room, this.seatCategory, 2, 1);

        this.genre = new Genre("test");
        this.movie = new Movie(2018, "test", "test", genre, 18, 120);

        this.presentationCategory = new PresentationCategory("test", "test");
        this.presentation = new Presentation(this.movie,  this.room, Calendar.getInstance().getTimeInMillis(), presentationCategory);
        this.priceCategory = new PriceCategory( this.presentationCategory,  this.seatCategory, "Test", "test", new BigDecimal("10.00"));

        Mockito.when(this.presentationDao.get( this.presentation.getUuid())).thenReturn( this.presentation);
        Mockito.when(this.seatDao.getAllByRoom(room)).thenReturn(Arrays.asList(seat1, seat2));
        Mockito.when(this.seatDao.get(this.seat1)).thenReturn(this.seat1);
        Mockito.when(this.seatDao.get(this.seat2)).thenReturn(this.seat2);

        this.busySeat1 = new BusySeat(true, this.seat1, this.presentation, false, "", 100);
        this.busySeat2 = new BusySeat(false, this.seat2, this.presentation, false, "", 100);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("p", this.presentation);
        map1.put("s", this.seat1);
        Mockito.when(this.busySeatDao.get(map1)).thenReturn(this.busySeat1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("p", this.presentation);
        map2.put("s", this.seat2);
        Mockito.when(this.busySeatDao.get(map2)).thenReturn(this.busySeat2);
        Mockito.when(this.busySeatDao.persistBatch(Arrays.asList(this.busySeat1, this.busySeat2))).thenReturn(true);
        Mockito.when(this.busySeatDao.persistBatch(Arrays.asList(this.busySeat1, Mockito.any()))).thenReturn(true);
        Mockito.when(this.busySeatDao.persistBatch(Arrays.asList(this.busySeat2))).thenReturn(false);


    }

    @Test
    public void testPersistUser() {
        User u = new User("", "test1235", new Role("test"));
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.WRONG_EMAIL);

        u = new User("tgw eklfw@fe.ew.de", "test1235", new Role("test"));
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.WRONG_EMAIL);

        Role role = new Role("User");
        u = new User("test22@gmx.de", "", role);
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.MISSING_FIRSTNAME);

        u.setFirstName("Max");
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.MISSING_LASTNAME);
        u.setLastName("Mustermann");
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.USER_NEEDS_PASSWORD);

        u = new User("test22@gmx.de", "te", role);
        u.setLastName("Mustermann");
        u.setFirstName("Max");
        assertEquals(this.reservationManager.persistUser(u), ResultMessage.USER_NEEDS_PASSWORD);
        assertEquals(this.reservationManager.persistUser(this.user2), this.user2);
        assertEquals(this.reservationManager.persistUser(this.user), this.user);

        u = new User("test@gmail.com", null,null);
        u.setLastName(this.user.getLastName());
        u.setFirstName(this.user.getFirstName());

        assertEquals(this.reservationManager.persistUser(u), ResultMessage.COULD_NOT_PERSIST_USER);

    }

    @Test
    public void testGetUser() {
        assertEquals(this.reservationManager.getUser(null, null), ResultMessage.WRONG_EMAIL);
        assertEquals(this.reservationManager.getUser("test@gmx.de", null), this.user);
        assertEquals(this.reservationManager.getUser("test22@gmx.de", null), ResultMessage.WRONG_PASSWORD);
        assertEquals(this.reservationManager.getUser("test22@gmx.de", "eef"), ResultMessage.WRONG_PASSWORD);
        assertEquals(this.reservationManager.getUser("test22@gmx.de", "124mgm"), this.user2);
    }

    @Test
    public void testGetReservation() {
        assertEquals(this.reservationManager.getReservation(null, 0), ResultMessage.WRONG_EMAIL);
        assertEquals(this.reservationManager.getReservation("test@gmx.de", 0), ResultMessage.RESERVATION_IS_NULL);
        assertEquals(this.reservationManager.getReservation("tewgwfw@web.de", 100), ResultMessage.USER_NOT_FOUND);
        assertEquals(this.reservationManager.getReservation("test@gmx.de", 22), ResultMessage.RESERVATION_IS_NULL);
        assertEquals(this.reservationManager.getReservation("test@gmx.de", 99), ResultMessage.RESERVATION_USER_UNMATCHING);
        assertEquals(this.reservationManager.getReservation("test@gmx.de", 100), this.reservation);
    }

    @Test
    public void testLockMechanism() {
        assertEquals(this.reservationManager.lockSeats("",null, "",true), ResultMessage.MISSING_PRESENTATION_ID);
        assertEquals(this.reservationManager.lockSeats("fwef",null, "",true), ResultMessage.NO_SEAT_TO_LOCK);
        assertEquals(this.reservationManager.lockSeats("wgwgw", Arrays.asList(this.seat1, this.seat2), "",true), ResultMessage.MISSING_SESSION_ID);
        assertEquals(this.reservationManager.lockSeats("wefwef", Arrays.asList(this.seat1, this.seat2), "testID",true), ResultMessage.PRESENTATION_NOT_FOUND);
        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1, new Seat(this.room, this.seatCategory, 1, 4)),"testID", true), ResultMessage.SEAT_NOT_FOUND);

        this.busySeat1.setBusy(true);
        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1, this.seat2),"testID", true), ResultMessage.SEAT_TAKEN);
        this.busySeat1.setBusy(false);
        this.busySeat1.setLocked(true);
        this.busySeat1.setSessionID("testID");
        this.busySeat1.setTimestamp(Calendar.getInstance().getTimeInMillis());
        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1, this.seat2),"test2ID", true), ResultMessage.LOCKED_SEAT);

        this.busySeat1.setLocked(false);
        this.busySeat1.setSessionID("");
        this.busySeat1.setTimestamp(0);
        Object result = this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1, this.seat2),"testID", true);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        List resultList = (List<Seat>) result;
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0), this.seat1);
        assertEquals(resultList.get(1), this.seat2);

        Seat s = new Seat(room, seatCategory, 1, 5);
        Mockito.when(this.seatDao.get(s)).thenReturn(s);

        result = this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1, s),"testID", true);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        resultList = (List<Seat>) result;
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0), this.seat1);
        assertEquals(resultList.get(1), s);

        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat2),"testID", true), ResultMessage.COULD_NOT_BLOCK_SEATS);

        this.busySeat1.setBusy(true);
        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1,this.seat2),"testID", false), ResultMessage.SEAT_TAKEN);
        this.busySeat1.setBusy(false);
        this.busySeat1.setLocked(true);
        this.busySeat1.setSessionID("testID");

        this.busySeat2.setBusy(false);
        this.busySeat2.setLocked(true);
        this.busySeat2.setSessionID("testID2");
        assertEquals(this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1,this.seat2),"testID2", false), ResultMessage.COULD_NOT_UNBLOCK_SEATS);


        this.busySeat2.setBusy(false);
        this.busySeat2.setLocked(true);
        this.busySeat2.setSessionID("testID");
        result = this.reservationManager.lockSeats(this.presentation.getUuid(), Arrays.asList(this.seat1,this.seat2),"testID", false);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        resultList = (List<Seat>) result;
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0), this.seat1);
        assertEquals(resultList.get(1), this.seat2);}


    @Test
    public void test2persistReservation(){
        assertEquals(this.reservationManager.persistReservation(null, ""), ResultMessage.RESERVATION_IS_NULL);
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.MISSING_TICKETS);
        this.reservation.setUser(null);
        Ticket ticket = new Ticket(this.seat1, this.priceCategory, this.presentation);
        this.reservation.setTickets(Arrays.asList(ticket));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.MISSING_USER);
        this.reservation.setUser(new User("test@gmail.com", "", this.role));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.USER_NOT_FOUND);

        this.reservation.setUser(new User(this.user2.getEmail(), "", this.role2));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.USER_NEEDS_PASSWORD);

        this.reservation.setUser(new User(this.user2.getEmail(), "geg", this.role2));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.WRONG_PASSWORD);

        ticket = new Ticket(this.seat1, this.priceCategory, new Presentation(this.movie, this.room, 1814912, this.presentationCategory));
        this.reservation.setUser(this.user);
        this.reservation.setTickets(Arrays.asList(ticket));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.PRESENTATION_NOT_FOUND);
        ticket = new Ticket(this.seat1, this.priceCategory, null);
        this.reservation.setTickets(Arrays.asList(ticket));
        assertEquals(this.reservationManager.persistReservation(this.reservation, ""), ResultMessage.PRESENTATION_NOT_SET);


    }

    @Test
    public void test6deleteReservation(){
        assertEquals(reservationManager.deleteReservation(reservation.getUser().getEmail(), reservation.getNumber()), ResultMessage.SUCCESS);
    }

}
