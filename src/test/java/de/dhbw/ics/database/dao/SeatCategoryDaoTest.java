package de.dhbw.ics.database.dao;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeatCategoryDaoTest {

    private static SeatCategory seatCategory;
    private static DaoTestHelper daoTestHelper;

    @Autowired
    private SeatCategoryDao seatCategoryDao;


    @BeforeClass
    public static void setUp() throws Exception {
        seatCategory = new SeatCategory("test", "testCategory");
        daoTestHelper = new DaoTestHelper();
    }

    @Test
    public void test1Persist() {
        daoTestHelper.persist(this.seatCategoryDao, seatCategory);
    }

    @Test
    public void test2Get() {
        daoTestHelper.get(this.seatCategoryDao, seatCategory, seatCategory.getUuid());
    }

    @Test
    public void test3GetAll() {
        daoTestHelper.getAll(this.seatCategoryDao, seatCategory);
    }

    @Test
    public void test4Update() {
        SeatCategory testSeatCategory = new SeatCategory(seatCategory.getUuid(), "otherTest", "otherDescription");
        daoTestHelper.update(this.seatCategoryDao, seatCategory.getUuid(), seatCategory, testSeatCategory);
    }

    @Test
    public void test5Delete() {
        daoTestHelper.delete(this.seatCategoryDao, seatCategory.getUuid());
    }

}
