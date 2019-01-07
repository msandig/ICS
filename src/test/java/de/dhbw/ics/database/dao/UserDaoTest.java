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

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

    private static User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PaymentMethodDao paymentMethodDao;

    @BeforeClass
    public static void setUp() throws Exception {
        user = new User("max@mustermann.de", "test1234", new Role("testUser"));
        user.setPaymentMethod(new PaymentMethod("testPayment", "testProvider"));
        user.setFirstName("Max");
        user.setLastName("Mustermann");
    }

    @Test
    public void test1Persist() {
        this.roleDao.persist(user.getRole());
        this.paymentMethodDao.persist(user.getPaymentMethod());
        DaoTestHelper.persist(this.userDao, user);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.userDao, user, user.getEmail());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.userDao, user);
    }

    @Test
    public void test4Update() throws CloneNotSupportedException {
        User u = user.clone();
        assertNotNull(u);
        u.setFirstName("Erika");
        DaoTestHelper.update(this.userDao, user.getEmail(), user, u);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.userDao, user.getEmail());
    }
}
