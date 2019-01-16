package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.PresentationCategory;
import de.dhbw.ics.vo.PriceCategory;
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
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceCategoryDaoTest {

    private static PriceCategory priceCategory;
    @Autowired
    private PriceCategoryDao priceCategoryDao;

    private static PresentationCategory presentationCategory;
    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    private static SeatCategory seatCategory;
    @Autowired
    private SeatCategoryDao seatCategoryDao;


    @BeforeClass
    public static void setUp() throws Exception {
        presentationCategory = new PresentationCategory("blockbuster", "the most advanced actors");
        seatCategory = new SeatCategory("Parkett", "seatsCloseToTheLeinwand");
        priceCategory = new PriceCategory( presentationCategory, seatCategory , "Low", "youDontHaveMoney", BigDecimal.valueOf(7.0));
    }

    @Test
    public void test1Persist() {
        DaoTestHelper.persist(this.presentationCategoryDao, presentationCategory);
        DaoTestHelper.persist(this.seatCategoryDao, seatCategory);
        DaoTestHelper.persist(this.priceCategoryDao, priceCategory);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.priceCategoryDao, priceCategory, priceCategory.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.priceCategoryDao, priceCategory);
    }

    @Test
    public void test4Update() {
        PriceCategory testPriceCategory = new PriceCategory(priceCategory.getUuid(), priceCategory.getPresentationCategory(),
                priceCategory.getSeatCategory(), "theBest", "otherCategoryThanElsewhere", BigDecimal.valueOf(14.0) );
        DaoTestHelper.update(this.priceCategoryDao, priceCategory.getUuid(), priceCategory, testPriceCategory);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.priceCategoryDao, priceCategory.getUuid());
    }

}
