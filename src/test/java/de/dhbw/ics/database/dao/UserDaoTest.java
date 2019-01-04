package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.PaymentMethod;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

    private static User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PaymentMethodDao paymentMethodDao;
    private boolean firstRun = true;

    @BeforeClass
    public static void setUp() throws Exception {
        user = new User("max@mustermann.de", "test1234", new Role("testUser"));
        user.setPaymentMethod(new PaymentMethod("testPayment", "testProvider"));
        user.setFirstName("Max");
        user.setLastName("Mustermann");
    }

    @Test
    public void test1Persist() {
        roleDao.persist(user.getRole());
        paymentMethodDao.persist(user.getPaymentMethod());
        assertTrue(userDao.persist(user));
    }

    @Test
    public void test2Get() {
        User u = this.userDao.get(user.getEmail());
        assertNotNull(u);
        assertEquals(user, u);
    }

    @Test
    public void test3GetAll() {
        List<User> userList = userDao.getAll();
        assertNotNull(userList);
        assertEquals(userList.size(), 1);
        assertEquals(user, userList.get(0));
    }

    @Test
    public void test4Update() throws CloneNotSupportedException {

        User u = user.clone();
        assertNotNull(u);
        u.setFirstName("Erika");
        assertTrue(userDao.persist(u));
        User u2 = userDao.get(u.getEmail());
        assertNotEquals(user, u2);
        assertEquals(u, u2);
    }

    @Test
    public void test5Delete() {
        assertTrue(userDao.delete(user.getEmail()));
        assertNull(userDao.get(user.getEmail()));
    }
}
