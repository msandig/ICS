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
public class RoleDaoTest {

    private static Role role;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PaymentMethodDao paymentMethodDao;

    @BeforeClass
    public static void setUp() throws Exception {
        role = new Role("testAdmin");
    }

    @Test
    public void test1Persist() {
        assertTrue(roleDao.persist(role));
    }

    @Test
    public void test2Get() {
        Role r = this.roleDao.get(role.getUuid());
        assertNotNull(r);
        assertEquals(role, r);

    }

    @Test
    public void test3GetAll() {
        List<Role> roleList = roleDao.getAll();
        assertNotNull(roleList);
        assertEquals(roleList.size(), 1);
        assertEquals(role, roleList.get(0));
    }

    @Test
    public void test4Update(){
        Role r = new Role(role.getUuid(), "otherRole");
        assertTrue(roleDao.persist(r));
        Role r2 = roleDao.get(r.getUuid());
        assertNotEquals(role, r2);
        assertEquals(r, r2);
    }

    @Test
    public void test5Delete() {
        assertTrue(roleDao.delete(role.getUuid()));
        assertNull(roleDao.get(role.getUuid()));
    }

    @Test
    public void test6DeleteWithDependencies() {
        assertTrue(roleDao.persist(role));
        PaymentMethod paymentMethod = new PaymentMethod("test", "test");
        assertTrue(paymentMethodDao.persist(paymentMethod));
        User user = new User("max@mustermann.de", "password", role);
        user.setPaymentMethod(paymentMethod);
        assertTrue(this.userDao.persist(user));
        assertFalse(roleDao.delete(role.getUuid()));
        assertTrue(userDao.delete(user.getEmail()));
        assertTrue(roleDao.delete(role.getUuid()));

    }
}
