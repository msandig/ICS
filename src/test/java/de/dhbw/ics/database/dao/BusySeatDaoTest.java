package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BusySeatDaoTest {

    private static BusySeat busySeat;
    private static Seat seat;
    private static String sessionId;
    private static  Presentation presentation;

    @BeforeClass
    public static void setUp(){
        busySeat = new BusySeat();
    }

    @Test
    public void test1lockedCheck() {
        busySeat.setLooked(true);
        assertTrue(busySeat.isLooked());

        busySeat.setLooked(false);
        assertFalse(busySeat.isLooked());
    }

    @Test
    public void test2BusyCheck(){
        busySeat.setBusy(true);
        assertTrue(busySeat.isBusy());

        busySeat.setBusy(false);
        assertFalse(busySeat.isBusy());
    }

    @Test
    public void test3SeatCheck(){
        Room room = new Room("kleiner Raum",false,true,23);
        SeatCategory seatCategory = new SeatCategory("Cooler Platz","Ein Platz zum Sitzen");
        seat = new Seat(room,seatCategory,3,5);

        busySeat.setSeat(seat);
        assertEquals(seat, busySeat.getSeat());
    }

    @Test
    public void test4Presentation(){
        Movie movie = new Movie(2018,"Der Hobbit","Ein Film",18,232);
        Genre genre = new Genre("Drama");
        movie.setGenre(genre);

        Room room = new Room("Mittleres 3D Kino", true, false,2);
        PresentationCategory presentationCategory = new PresentationCategory("normal","3D Vorstellung");

        presentation = new Presentation(movie,room,232312,presentationCategory);

        busySeat.setPresentation(presentation);
        assertEquals(presentation,busySeat.getPresentation());
    }

    @Test
    public void test5SessionID(){
        sessionId = "239d2938dh392d93";
        busySeat.setSessionID(sessionId);
        assertEquals(sessionId,busySeat.getSessionID());
    }

    @Test
    public void test6timestamp(){
        long timestamp = 232343522;
        busySeat.setTimestamp(timestamp);
        assertEquals(timestamp,busySeat.getTimestamp());
    }

    @Test
    public void test7getUUIDs(){
        assertEquals(seat.getUuid(), busySeat.getSeatUUID());
        assertEquals(sessionId, busySeat.getSessionID());
        assertEquals(presentation.getUuid(),busySeat.getPresentationUUID());
    }

    @Test
    public void test8equels(){
        assertTrue(busySeat.equals(busySeat));
        BusySeat busySeatColne = new BusySeat();

        busySeatColne.setTimestamp(busySeat.getTimestamp());
        busySeatColne.setSessionID(busySeat.getSessionID());
        busySeatColne.setPresentation(busySeat.getPresentation());
        busySeatColne.setSeat(busySeat.getSeat());
        busySeatColne.setLooked(busySeat.isLooked());
        busySeatColne.setBusy(busySeat.isBusy());

        assertTrue(busySeat.equals(busySeatColne));
    }

    @Test
    public void test9hashcode(){
        int hashCode = -438449964;
        assertEquals(hashCode,busySeat.hashCode());
    }
}
