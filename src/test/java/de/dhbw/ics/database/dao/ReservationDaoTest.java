package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Reservation;
import de.dhbw.ics.vo.Role;
import de.dhbw.ics.vo.User;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservationDaoTest {

    private static Reservation reservation;
    private static String uuid;
    private static Role role;
    private static User user;
    private static Reservation cloneReservation;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @BeforeClass
    public static void setUp(){
        uuid = UUID.randomUUID().toString();
        reservation = new Reservation(uuid,12,1212208,true);
    }

    @Test
    public void test1UUID(){
        assertEquals(uuid,reservation.getUuid());
    }

    @Test
    public void test2User(){
        role = new Role("Nutzer");
        user = new User("user@email.com","password1234",role);
        reservation.setUser(user);

        assertEquals(user,reservation.getUser());
    }

    @Test
    public void test3persist(){
        roleDao.persist(role);
        userDao.persist(user);
        DaoTestHelper.persist(reservationDao,reservation);
    }

    @Test
    public void test4get(){
        DaoTestHelper.get(reservationDao,reservation,reservation.getUuid());
    }

    @Test
    public void test5getAll(){
        DaoTestHelper.getAll(reservationDao,reservation);
    }

    @Test
    public void test6update(){
        cloneReservation = new Reservation("",21,23232110,true );
        cloneReservation.setUser(user);
        DaoTestHelper.update(reservationDao,reservation.getUuid(),reservation,cloneReservation);
    }

    @Test
    public void test7delete() {
        DaoTestHelper.delete(reservationDao,reservation.getUuid());
    }
}
