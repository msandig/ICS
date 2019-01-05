package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Room;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomDaoTest {

    private static Room room;
    private static DaoTestHelper daoTestHelper;

    @Autowired
    private RoomDao roomDao;

    @BeforeClass
    public static void setUp() throws Exception {
        room = new Room("niceRoom", true, false, 5);
        daoTestHelper = new DaoTestHelper();
    }

    @Test
    public void test1Persist() {
        daoTestHelper.persist(this.roomDao, room);
    }

    @Test
    public void test2Get() {
        daoTestHelper.get(this.roomDao, room, room.getUuid());
    }

    @Test
    public void test3GetAll() {
        daoTestHelper.getAll(this.roomDao, room);
    }

    @Test
    public void test4Update() {
        Room testRoom = new Room(room.getUuid(),"testRoom", true, false, 1);
        daoTestHelper.update(this.roomDao, room.getUuid(), room, testRoom);
    }

    @Test
    public void test5Delete() {
        daoTestHelper.delete(this.roomDao, room.getUuid());
    }


}
