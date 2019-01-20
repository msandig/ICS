/*
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusyBusySeatDaoTest {

    private static BusySeat busySeat;

    @Autowired
    private BusySeatDao busySeatDao;
    private static Movie movie;
    @Autowired
    private MovieDao movieDao;
    private static Genre genre;
    @Autowired
    private GenreDao genreDao;
    private static Room room;
    @Autowired
    private RoomDao roomDao;
    private static PresentationCategory presentationCategory;
    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    private static Presentation presentation;
    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private RoomDao roomDao;

    @BeforeClass
    public static void setUp() throws Exception {
        busySeat = new BusySeat(true, );
        //busySeat.getRoom().getBusySeats().put(busySeat.getUuid(), busySeat);
    }

    @Test
    public void test1Persist() {
        assertFalse(this.busySeatDao.persist(busySeat));
        assertTrue(this.roomDao.persist(busySeat.getPresentation().getRoom()));
        assertTrue(this.busySeatCategoryDao.persist(busySeat.getBusySeatCategory()));
        DaoTestHelper.persist(busySeatDao, busySeat);
    }

    @Test
    public void test2Get() {
        assertNull(this.busySeatDao.get(busySeat.getUuid()));
        DaoTestHelper.get(this.busySeatDao, busySeat, busySeat);
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.busySeatDao, busySeat);
    }

    @Test
    public void test4Update() {
        BusySeat testBusySeat = new BusySeat(busySeat.getUuid(), busySeat.getRoom(), busySeat.getBusySeatCategory(), 5, 1);
        DaoTestHelper.update(this.busySeatDao, busySeat, busySeat, testBusySeat);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.busySeatDao, busyBusyBusySeat.getUuid());
    }

}
*/
