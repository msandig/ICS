package de.dhbw.ics.manager;

import de.dhbw.ics.controller.web.ResultMessage;
import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.*;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PresentationManagerTest {

    @TestConfiguration
    static class PresentationManagerTestContextConfiguration {

        @Bean
        public PresentationManager presentationManager() {
            return new PresentationManager();
        }
    }

    @MockBean
    private PresentationDao presentationDao;

    @MockBean
    private SeatDao seatDao;

    @MockBean
    private BusySeatDao busySeatDao;

    @MockBean
    private MovieDao movieDao;

    @MockBean
    private PriceCategoryDao priceCategoryDao;

    @Autowired
    private PresentationManager presentationManager;
    private PriceCategory priceCategory;
    private Presentation presentation;
    private Movie movie;
    private Genre genre;
    private Seat seat1;
    private Seat seat2;
    private SeatCategory seatCategory;
    private Room room;
    private PresentationCategory presentationCategory;


    @Before
    public void setUp() {

        this.room = new Room("3D", true, true, 1);
        this.seatCategory = new SeatCategory("test", "test");

        this.seat1 = new Seat(this.room, this.seatCategory, 1, 1);
        this.seat2 = new Seat(this.room, this.seatCategory, 2, 1);

        this.genre = new Genre("test");
        this.movie = new Movie(2018, "test", "test", genre, 18, 120);

        this.presentationCategory = new PresentationCategory("test", "test");
        this.presentation = new Presentation(this.movie, this.room, Calendar.getInstance().getTimeInMillis(), this.presentationCategory);
        this.priceCategory = new PriceCategory(this.presentationCategory, this.seatCategory, "Test", "test", new BigDecimal("10.00"));

        Mockito.when(this.priceCategoryDao.getAll()).thenReturn(Arrays.asList(this.priceCategory));

        Mockito.when(this.presentationDao.get(presentation.getUuid())).thenReturn(this.presentation);
        Mockito.when(this.presentationDao.getAll()).thenReturn(Arrays.asList(this.presentation));
        Mockito.when(this.presentationDao.delete(presentation.getUuid())).thenReturn(true);
        Mockito.when(this.presentationDao.getPresentationsByMovieAndDateInterval(1, 1, this.movie.getUuid())).thenReturn(Arrays.asList(this.presentation));
        Mockito.when(this.presentationDao.getPresentationsByTitleAndDateInterval(1, 1, this.movie.getTitle())).thenReturn(Arrays.asList(this.presentation));
        Mockito.when(this.presentationDao.getPresentationsBetweenInterval(1, 2)).thenReturn(Arrays.asList(this.presentation));
        Mockito.when(this.presentationDao.persist(presentation)).thenReturn(true);

        Mockito.when(this.movieDao.getAll()).thenReturn(Arrays.asList(this.movie));
        Mockito.when(this.movieDao.getMovieByTitle(this.movie.getTitle())).thenReturn(Arrays.asList(this.movie));
        Mockito.when(this.movieDao.getMoviesBetweenDate(1, 2)).thenReturn(Arrays.asList(this.movie));

        Mockito.when(this.seatDao.getAllByRoom(this.room)).thenReturn(Arrays.asList(this.seat1, this.seat2));
        Mockito.when(this.busySeatDao.getAllByPresentation(this.presentation)).thenReturn(null);


    }

    @Test
    public void testGetAllPriceCategories() {
        Object result = this.presentationManager.getAllPriceCategories();
        assertNotNull(result);
        assertEquals(this.priceCategory, ((List<PriceCategory>) result).get(0));
    }

    @Test
    public void testGetAllPresentations() {
        Object result = this.presentationManager.getAllPresentations();
        assertNotNull(result);
        assertEquals(this.presentation, ((List<Presentation>) result).get(0));

        result = this.presentationManager.getAllPresentations(0, 0);
        assertNotNull(result);
        assertThat(result, CoreMatchers.instanceOf(String.class));
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentations(1, 2);
        assertEquals(this.presentation, ((List<Presentation>) result).get(0));

    }

    @Test
    public void testGetAllMovies() {
        Object result = this.presentationManager.getAllMovies();
        assertNotNull(result);
        assertEquals(this.movie, ((List<Movie>) result).get(0));
    }

    @Test
    public void testGetAllMoviesByTitle() {
        Object result = this.presentationManager.getAllMoviesByTitle(null);
        assertNotNull(result);
        assertEquals(ResultMessage.MISSING_MOVIE_TITLE, result);
        result = this.presentationManager.getAllMoviesByTitle("");
        assertNotNull(result);
        assertEquals(ResultMessage.MISSING_MOVIE_TITLE, result);

        result = this.presentationManager.getAllMoviesByTitle("tes");
        assertNotNull(result);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertEquals(0, ((List) result).size());

        result = this.presentationManager.getAllMoviesByTitle(this.movie.getTitle());
        assertEquals(this.movie, ((List<Movie>) result).get(0));
    }

    @Test
    public void testGetAllPresentationsByMovie() {
        Object result = this.presentationManager.getAllPresentationsByMovie(0, 0, null);
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByMovie(0, 0, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByMovie(1, 0, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByMovie(1, 1, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByMovie(1, 1, "tewc");
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertEquals(0, ((List) result).size());

        result = this.presentationManager.getAllPresentationsByMovie(1, 1, this.movie.getUuid());
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertNotEquals(0, ((List) result).size());
        assertEquals(this.presentation, ((List<Presentation>) result).get(0));

    }

    @Test
    public void testGetAllMoviesBetweenInterval() {
        Object result = this.presentationManager.getAllMoviesBetweenInterval(0, 0);
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllMoviesBetweenInterval(1, 0);
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllMoviesBetweenInterval(1, 1);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertEquals(0, ((List) result).size());

        result = this.presentationManager.getAllMoviesBetweenInterval(1, 2);
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertNotEquals(0, ((List) result).size());
        assertEquals(this.movie, ((List<Presentation>) result).get(0));
    }

    @Test
    public void testPersistPresentation() {
        Object result = this.presentationManager.persistPresentation(null);
        assertEquals(ResultMessage.MISSING_PRESENTATION_TO_PERSIST, result);

        Presentation p = new Presentation("", presentation.getMovie(), this.presentation.getRoom(), this.presentation.getDate(), this.presentation.getPresentationCategory());
        result = this.presentationManager.persistPresentation(p);
        assertEquals(ResultMessage.MISSING_PRESENTATION_ID, result);

        p = new Presentation(this.presentation);
        p.setRoom(null);
        result = this.presentationManager.persistPresentation(p);
        assertEquals(ResultMessage.MISSING_ROOM, result);

        result = this.presentationManager.persistPresentation(presentation);
        assertEquals(ResultMessage.DATE_IN_PAST, result);

        presentation.setDate(this.presentation.getDate() + 3600000);

        p = new Presentation(this.presentation);
        p.setMovie(null);
        result = this.presentationManager.persistPresentation(p);
        assertEquals(ResultMessage.MISSING_MOVIE, result);

        p = new Presentation(this.presentation);
        p.setPresentationCategory(null);
        result = this.presentationManager.persistPresentation(p);
        assertEquals(ResultMessage.MISSING_PRESENTATION_CATEGORY, result);

        result = this.presentationManager.persistPresentation(this.presentation);
        assertEquals(this.presentation, result);

        p = new Presentation(this.presentation.getMovie(), this.presentation.getRoom(), this.presentation.getDate(), this.presentation.getPresentationCategory());
        result = this.presentationManager.persistPresentation(p);
        assertEquals(ResultMessage.COULD_NOT_PERSIST_PRESENTATION, result);
    }

    @Test
    public void testGetPresentation() {
        Object result = this.presentationManager.getPresentation(null);
        assertEquals(ResultMessage.MISSING_PRESENTATION_ID, result);

        result = this.presentationManager.getPresentation("");
        assertEquals(ResultMessage.MISSING_PRESENTATION_ID, result);

        result = this.presentationManager.getPresentation(presentation.getUuid());
        assertEquals(presentation, result);
    }

    @Test
    public void testDeletePresentation() {
        Object result = this.presentationManager.deletePresentation(null);
        assertEquals(ResultMessage.MISSING_PRESENTATION_ID, result);

        result = this.presentationManager.deletePresentation("");
        assertEquals(ResultMessage.MISSING_PRESENTATION_ID, result);

        result = this.presentationManager.deletePresentation(presentation.getUuid());
        assertEquals(ResultMessage.SUCCESS, result);

        result = this.presentationManager.deletePresentation("test");
        assertEquals(ResultMessage.COULD_NOT_DELETE_PRESENTATION, result);
    }

    @Test
    public void testGetAllPresentationsByTitle() {

        Object result = this.presentationManager.getAllPresentationsByTitle(0, 0, null);
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByTitle(0, 0, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByTitle(1, 0, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByTitle(1, 1, "");
        assertNotNull(result);
        assertEquals(ResultMessage.WRONG_PARAMETERS, result);

        result = this.presentationManager.getAllPresentationsByTitle(1, 1, "fewfwe");
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertEquals(0, ((List) result).size());

        result = this.presentationManager.getAllPresentationsByTitle(1, 1, movie.getTitle());
        assertThat(result, CoreMatchers.instanceOf(List.class));
        assertNotEquals(0, ((List) result).size());
        assertEquals(this.presentation, ((List<Presentation>) result).get(0));


    }
}
