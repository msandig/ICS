package de.dhbw.ics.controller.web;

import de.dhbw.ics.database.dao.GenreDao;
import de.dhbw.ics.database.dao.MovieDao;
import de.dhbw.ics.spring.ApplicationContextProvider;
import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class MovieController {

    @RequestMapping(method=RequestMethod.GET, path = "/get/movies")
    public @ResponseBody
    ResponseEntity<List<Movie>> getMovies() {
        MovieDao movieDao = new MovieDao();
        GenreDao genreDao = new GenreDao();
        genreDao.setDataSource(ApplicationContextProvider.getContext().getBean("dataSource", DataSource.class));
        Genre genreDrama = new Genre("Drama");
        genreDao.persist(genreDrama);
        movieDao.setDataSource(ApplicationContextProvider.getContext().getBean("dataSource", DataSource.class));
        Movie movieObj = new Movie(2001,"unglaublicher Film","Der unglaublichste Film aller Zeiten",18,124);
        movieObj.setGenre(genreDrama);
        movieDao.persist(movieObj);
        List<Movie> movieList = movieDao.getAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @PostMapping("/post/movie")
    public Movie setMovie(@RequestBody Movie bodyMovie){

        GenreDao genreDao = new GenreDao();
        genreDao.setDataSource(ApplicationContextProvider.getContext().getBean("dataSource", DataSource.class));
        List<Genre> genreList = genreDao.getAll();

        MovieDao movieDao = new MovieDao();
        movieDao.setDataSource(ApplicationContextProvider.getContext().getBean("dataSource", DataSource.class));
        Movie newMovie = new Movie(bodyMovie.getProductionYear(),bodyMovie.getTitle(),bodyMovie.getDescription(),bodyMovie.getFsk(),bodyMovie.getRunTime(),genreList.get(0));
        movieDao.persist(newMovie);

        return newMovie;
    }

}
