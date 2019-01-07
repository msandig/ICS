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
    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    @BeforeClass
    public static void setUp() throws Exception {
        presentationCategory = new PresentationCategory("testCategory", "test");
    }

    @Test
    public void test1Persist() {
        DaoTestHelper.persist(this.presentationCategoryDao, presentationCategory);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.presentationCategoryDao, presentationCategory, presentationCategory.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.presentationCategoryDao, presentationCategory);
    }

    @Test
    public void test4Update() {
        PresentationCategory testPresentationCategory = new PresentationCategory(presentationCategory.getUuid(), "otherCategoryName", "testCahnged");
        DaoTestHelper.update(this.presentationCategoryDao, presentationCategory.getUuid(), presentationCategory, testPresentationCategory);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.presentationCategoryDao, presentationCategory.getUuid());
    }

}
