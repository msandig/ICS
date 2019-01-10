package de.dhbw.ics.controller.web;

import de.dhbw.ics.database.dao.GenreDao;
import de.dhbw.ics.database.dao.MovieDao;
import de.dhbw.ics.spring.ApplicationContextProvider;
import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private MovieDao movieDao = ApplicationContextProvider.getContext().getBean("movieDao", MovieDao.class);
    private GenreDao genreDao = ApplicationContextProvider.getContext().getBean("genreDao", GenreDao.class);

    @RequestMapping(method=RequestMethod.GET, path = "/get/movies")
    public @ResponseBody
    ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movieList = movieDao.getAll();

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @PostMapping("/post/movie")
    public void setMovie(@RequestBody Movie bodyMovie){

        Movie newMovie = new Movie(bodyMovie.getProductionYear(),bodyMovie.getTitle(),bodyMovie.getDescription(),bodyMovie.getFsk(), bodyMovie.getRunTime(), bodyMovie.getGenre());
        movieDao.persist(newMovie);

    }
}
