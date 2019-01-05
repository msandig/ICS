package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;
import de.dhbw.ics.vo.SeatCategory;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeatDaoTest {

    private static Seat seat;
    private static DaoTestHelper daoTestHelper;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private SeatCategoryDao seatCategoryDao;

    @Autowired
    private RoomDao roomDao;

    @BeforeClass
    public static void setUp() throws Exception {
        seat = new Seat(new Room("niceRoom", true, false, 5), new SeatCategory("testCategory", "test"), 1, 12);
        seat.getRoom().getSeats().put(seat.getUuid(), seat);
        daoTestHelper = new DaoTestHelper();
    }

    @Test
    public void test1Persist() {
        assertFalse(this.seatDao.persist(seat));
        assertTrue(this.roomDao.persist(seat.getRoom()));
        assertTrue(this.seatCategoryDao.persist(seat.getSeatCategory()));
        daoTestHelper.persist(seatDao, seat);
    }

    @Test
    public void test2Get() {
        assertNull(this.seatDao.get(seat.getUuid()));
        daoTestHelper.get(this.seatDao, seat, seat);
    }

    @Test
    public void test3GetAll() {
        daoTestHelper.getAll(this.seatDao, seat);
    }

    @Test
    public void test4Update() {
        Seat testSeat = new Seat(seat.getUuid(), seat.getRoom(), seat.getSeatCategory(), 5, 1);
        daoTestHelper.update(this.seatDao, seat, seat, testSeat);
    }

    @Test
    public void test5Delete() {
        daoTestHelper.delete(this.seatDao, seat.getUuid());
    }

}
