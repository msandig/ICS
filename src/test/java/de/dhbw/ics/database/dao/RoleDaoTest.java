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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
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
        DaoTestHelper.persist(this.roleDao, role);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.roleDao, role, role.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.roleDao, role);
    }

    @Test
    public void test4Update() {
        Role changedRole = new Role(role.getUuid(), "otherRole");
        DaoTestHelper.update(this.roleDao,role.getUuid(), role, changedRole);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.roleDao, role.getUuid());
    }

    @Test
    public void test6DeleteWithDependencies() {
        assertTrue(this.roleDao.persist(role));
        PaymentMethod paymentMethod = new PaymentMethod("test", "test");
        assertTrue(this.paymentMethodDao.persist(paymentMethod));
        User user = new User("max@mustermann.de", "password", role);
        user.setPaymentMethod(paymentMethod);
        assertTrue(this.userDao.persist(user));
        assertFalse(this.roleDao.delete(role.getUuid()));
        assertTrue(this.userDao.delete(user.getEmail()));
        assertTrue(this.roleDao.delete(role.getUuid()));

    }
}
