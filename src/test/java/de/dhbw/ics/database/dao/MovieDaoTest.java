package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
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
public class MovieDaoTest {

    private static Movie movie;
    private static Genre genre;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private MovieDao movieDao;

    @BeforeClass
    public static void setUp() throws Exception {
        movie = new Movie(2015, "TestMovie", "Nice Test Movie", 12, 120);
        genre = new Genre("testGenre");
        movie.setGenre(genre);
    }

    @Test
    public void test1Persist() {
        assertFalse(this.movieDao.persist(movie));
        assertTrue(this.genreDao.persist(genre));
        DaoTestHelper.persist(this.movieDao, movie);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.movieDao, movie, movie.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.movieDao, movie);
    }

    @Test
    public void test4Update() {
        Movie testMovie = new Movie(movie.getUuid(), 2000,"updatedMovie", movie.getDescription(), movie.getFsk(), movie.getRunTime());
        testMovie.setGenre(movie.getGenre());
        DaoTestHelper.update(this.movieDao, movie.getUuid(), movie, testMovie);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.movieDao, movie.getUuid());
    }
}
