package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
import de.dhbw.ics.vo.Presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PresentationController {

    @Autowired
    private PresentationManager presentationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<Object> get(@PathVariable String id) {
        Object result = null;
        if (id != null && !id.isEmpty()) {
            result = this.presentationManager.getPresentation(id);
        }
        if (result instanceof Presentation) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/presentations")
    public @ResponseBody
    ResponseEntity<Object> getAll(@RequestParam(value = "start") Optional<Long> start, @RequestParam(value = "end") Optional<Long> end, @RequestParam(value = "title") Optional<String> title, @RequestParam(value = "movie") Optional<String> movie) {
        Object result = null;
        if (start.isPresent() && end.isPresent() && !title.isPresent() && !movie.isPresent()) {
            result = this.presentationManager.getAllPresentations(start.get(), end.get());
        } else if (start.isPresent() && end.isPresent() && title.isPresent() && !movie.isPresent()) {
            result = this.presentationManager.getAllPresentationsByTitle(start.get(), end.get(), title.get());
        } else if (!start.isPresent() && !end.isPresent() && !title.isPresent() && !movie.isPresent()) {
            result = this.presentationManager.getAllPresentations();
        } else if (start.isPresent() && end.isPresent() && !title.isPresent() && movie.isPresent()) {
            result = this.presentationManager.getAllPresentationsByMovie(start.get(), end.get(), movie.get());
        }

        if (result instanceof List) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/presentations", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody Presentation presentation) {
        Object result = this.presentationManager.persistPresentation(presentation);
        if (result instanceof Presentation) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable String id) {
        String result = null;
        if (id != null && !id.isEmpty()) {
            result = this.presentationManager.deletePresentation(id);
        }
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(ResultMessage.FAILED, HttpStatus.EXPECTATION_FAILED);
    }


}
