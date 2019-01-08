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

    @Autowired
    private SeatCategoryDao seatCategoryDao;


    @BeforeClass
    public static void setUp() throws Exception {
        seatCategory = new SeatCategory("test", "testCategory");
    }

    @Test
    public void test1Persist() {
        DaoTestHelper.persist(this.seatCategoryDao, seatCategory);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.seatCategoryDao, seatCategory, seatCategory.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.seatCategoryDao, seatCategory);
    }

    @Test
    public void test4Update() {
        SeatCategory testSeatCategory = new SeatCategory(seatCategory.getUuid(), "otherTest", "otherDescription");
        DaoTestHelper.update(this.seatCategoryDao, seatCategory.getUuid(), seatCategory, testSeatCategory);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.seatCategoryDao, seatCategory.getUuid());
    }

}
