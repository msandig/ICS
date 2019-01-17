package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
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
        Object result = null;
        if (title.isPresent() && !end.isPresent() && !start.isPresent()) {
            result = this.presentationManager.getAllMoviesByTitle(title.get());

        } else if (!title.isPresent() && end.isPresent() && start.isPresent()) {
            result = this.presentationManager.getAllMoviesBetweenInterval(start.get(), end.get());
        } else {
            result = this.presentationManager.getAllMovies();
        }
        if (result instanceof List) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result , HttpStatus.EXPECTATION_FAILED);
        }
    }

}
