package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
import de.dhbw.ics.vo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private PresentationManager presentationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/movies")
    public @ResponseBody
    ResponseEntity<Object> getAll(Optional<String> title) {
        List<Movie> movieList = null;
        if (title.isPresent()) {
            movieList = this.presentationManager.getAllMoviesByTitle(title.get());

        } else {
            movieList = this.presentationManager.getAllMovies();
        }
        if (movieList == null) {
            return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

}
