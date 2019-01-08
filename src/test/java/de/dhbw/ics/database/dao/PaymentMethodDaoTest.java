package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.PaymentMethod;
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
public class PaymentMethodDaoTest {

    private static PaymentMethod paymentMethod;

    @Autowired
    private PaymentMethodDao paymentMethodDao;


    @BeforeClass
    public static void setUp() throws Exception {
        paymentMethod = new PaymentMethod("test", "testProvider");
    }

    @Test
    public void test1Persist() {
        DaoTestHelper.persist(this.paymentMethodDao, paymentMethod);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.paymentMethodDao, paymentMethod, paymentMethod.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.paymentMethodDao, paymentMethod);
    }

    @Test
    public void test4Update() {
        PaymentMethod testPaymentMathod = new PaymentMethod(paymentMethod.getUuid(), "otherTest", "otherProvider");
        DaoTestHelper.update(this.paymentMethodDao, paymentMethod.getUuid(), paymentMethod, testPaymentMathod);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.paymentMethodDao, paymentMethod.getUuid());
    }

}
