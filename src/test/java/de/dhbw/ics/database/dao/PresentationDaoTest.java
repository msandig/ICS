package de.dhbw.ics.database.dao;

import de.dhbw.ics.vo.*;
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
public class PresentationDaoTest {

    private static Movie movie;
    @Autowired
    private MovieDao movieDao;
    private static Genre genre;
    @Autowired
    private GenreDao genreDao;
    private static Room room;
    @Autowired
    private RoomDao roomDao;
    private static PresentationCategory presentationCategory;
    @Autowired
    private PresentationCategoryDao presentationCategoryDao;

    private static Presentation presentation;
    @Autowired
    private PresentationDao presentationDao;

    @BeforeClass
    public static void setUp() throws Exception {
        movie = new Movie(2018, "The Chase", "The Chase is better than the catch", 12, 123);
        movie.setPicture("/path");
        genre = new Genre("Doku");
        room = new Room("someRoom", true, false, 3);
        presentationCategory = new PresentationCategory("Preview", "This movie was never shown before");
        presentation = new Presentation(movie, room, 13122018 ,presentationCategory);
    }

    @Test
    public void test1Persist() {
        genreDao.persist(genre);
        movie.setGenre(genre);
        movieDao.persist(movie);
        roomDao.persist(room);
        presentationCategoryDao.persist(presentationCategory);

        DaoTestHelper.persist(this.presentationDao, presentation);
    }

    @Test
    public void test2Get() {
        DaoTestHelper.get(presentationDao, presentation, presentation.getUuid());
    }

    @Test
    public void test3GetAll() {
        DaoTestHelper.getAll(presentationDao, presentation);
    }

    @Test
    public void test4Update() {
        Movie anotherMovie = new Movie(1999, "Hyper", "the first chapter", genre, 12, 135);
        movieDao.persist(anotherMovie);
        Presentation anotherPresentation = new Presentation(presentation.getUuid(), anotherMovie, presentation.getRoom(), presentation.getDate(), presentation.getPresentationCategory());
        DaoTestHelper.update(presentationDao, presentation.getUuid(), presentation, anotherPresentation);
    }

    @Test
    public void test5Delete() {
        DaoTestHelper.delete(presentationDao, presentation.getUuid());

    }

    @Test
    public void test6DeleteWithDependencies() {
        assertTrue(movieDao.delete(movie.getUuid()));
        assertTrue(roomDao.delete(room.getUuid()));
        assertFalse(genreDao.delete(genre.getUuid()));
        assertTrue(presentationCategoryDao.delete(presentationCategory.getUuid()));
    }
}