package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.Genre;
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
public class GenreDaoTest {
    private static Genre genre;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private MovieDao movieDao;

    @BeforeClass
    public static void setUp() throws Exception {
        genre = new Genre("testGenre");
    }

    @Test
    public void test1Persist() {
        DaoTestHelper.persist(this.genreDao, genre);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(this.genreDao, genre, genre.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(this.genreDao, genre);
    }

    @Test
    public void test4Update() {
        Genre changedGenre = new Genre(genre.getUuid(), "otherGenre");
        DaoTestHelper.update(this.genreDao, genre.getUuid(), genre, changedGenre);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(this.genreDao, genre.getUuid());
    }

    @Test
    public void test6DeleteWithDependencies() {

    }
}

