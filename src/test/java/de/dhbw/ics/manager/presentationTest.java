package de.dhbw.ics.manager;

import de.dhbw.ics.database.dao.DaoTestHelper;
import de.dhbw.ics.database.dao.MovieDao;
import de.dhbw.ics.database.dao.PresentationDao;
import de.dhbw.ics.database.dao.PriceCategoryDao;
import de.dhbw.ics.manager.PresentationManager;
import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
import de.dhbw.ics.vo.Presentation;

import org.junit.Assert;

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
@ContextConfiguration("classpath:spring/applicationContextDaoTest.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class presentationTest {

    private static PresentationManager presentationManager;

    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private PriceCategoryDao priceCategoryDao;

    @Autowired
    private MovieDao movieDao;

    @BeforeClass
    public static void setUp(){
        presentationManager = new PresentationManager();
    }

    @Test
    public void test1getAllPriceCategories(){
        assertEquals(priceCategoryDao.getAll(),presentationManager.getAllPriceCategories());
    }

    @Test
    public void test2getAllPresentation(){
        assertEquals(presentationDao.getAll(),presentationManager.getAllPresentations());
    }

    @Test
    public void test3getAllMovies(){
        assertEquals(movieDao.getAll(),presentationManager.getAllMovies());
    }

    @Test
    public void test4getMovieByTitle(){
        Movie movie = new Movie(2015, "TestMovie23", "Nice Test Movie", 12, 120);
        Genre genre = new Genre("testGenre");
        movie.setGenre(genre);
        DaoTestHelper.persist(movieDao,movie);

        assertEquals(movie,presentationManager.getAllMoviesByTitle("TestMovie23"));
    }

    @Test
    public void test5getAllPresentation() {
        //assertEquals(presentationDao.getAll(),presentationManager.getAllPresentations());
        List<Presentation> presentationList = presentationManager.getAllPresentations();
        assertNotNull(presentationList);
    }

    @Test
    public void test6getAllPresentationByTime(){

    }

    @Test
    public void test7getPresentationById(){

    }

    @Test
    public void test8getAllMoviesBetweenInterval(){

    }


}
