package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.PresentationCategory;
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
public class PresentationCategoryDaoTest {

    private static PresentationCategory presentationCategory;
    private static DaoTestHelper daoTestHelper;

    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    @BeforeClass
    public static void setUp() throws Exception {
        presentationCategory = new PresentationCategory("testCategory", "test");
        daoTestHelper = new DaoTestHelper();
    }

    @Test
    public void test1Persist() {
        daoTestHelper.persist(this.presentationCategoryDao, presentationCategory);
    }

    @Test
    public void test2Get() {
        daoTestHelper.get(this.presentationCategoryDao, presentationCategory, presentationCategory.getUuid());
    }

    @Test
    public void test3GetAll() {
        daoTestHelper.getAll(this.presentationCategoryDao, presentationCategory);
    }

    @Test
    public void test4Update() {
        PresentationCategory testPresentationCategory = new PresentationCategory(presentationCategory.getUuid(), "otherCategoryName", "testCahnged");
        daoTestHelper.update(this.presentationCategoryDao, presentationCategory.getUuid(), presentationCategory, testPresentationCategory);
    }

    @Test
    public void test5Delete() {
        daoTestHelper.delete(this.presentationCategoryDao, presentationCategory.getUuid());
    }

}
