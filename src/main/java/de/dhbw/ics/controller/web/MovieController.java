package de.dhbw.ics.controller.web;

import de.dhbw.ics.vo.Genre;
import de.dhbw.ics.vo.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {


    @RequestMapping(method=RequestMethod.GET, path = "/get/movie")
    public @ResponseBody
    ResponseEntity<Movie> getMovie() {
        Movie movie = new Movie(2015, "TestMovie", "Nice Test Movie", 12, 120);
        Genre genre = new Genre("testGenre");
        movie.setGenre(genre);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

}
