package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
import de.dhbw.ics.vo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private PresentationManager presentationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/movies")
    public @ResponseBody
    ResponseEntity<Object> getAll(@RequestParam(value = "start") Optional<Long> start, @RequestParam(value = "end") Optional<Long> end, @RequestParam(value = "title") Optional<String> title) {
        List<Movie> movieList = null;
        if (title.isPresent() && !end.isPresent() && !start.isPresent()) {
            movieList = this.presentationManager.getAllMoviesByTitle(title.get());

        } else if (!title.isPresent() && end.isPresent() && start.isPresent()) {
            movieList = this.presentationManager.getAllMoviesBetweenInterval(start.get(), end.get());
        } else {
            movieList = this.presentationManager.getAllMovies();
        }
        if (movieList == null) {
            return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

}
