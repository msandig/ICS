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
    ResponseEntity<Presentation> get(@PathVariable String id) {
        Presentation presentation = null;
        if (id != null && !id.isEmpty()) {
            presentation = this.presentationManager.getPresentation(id);
        }
        return new ResponseEntity<>(presentation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/presentations")
    public @ResponseBody
    ResponseEntity<Object> getAll(@RequestParam(value = "start") Optional<Long> start, @RequestParam(value = "end") Optional<Long> end, Optional<String> title, Optional<String> movie) {
        List<Presentation> presentations = null;
        if (start.isPresent() && end.isPresent() && !title.isPresent() && !movie.isPresent()) {
            presentations = this.presentationManager.getAllPresentations(start.get(), end.get());
        } else if (start.isPresent() && end.isPresent() && title.isPresent() && !movie.isPresent()) {
            presentations = this.presentationManager.getAllPresentationsByTitle(start.get(), end.get(), title.get());
        } else if (!start.isPresent() && !end.isPresent() && !title.isPresent() && !movie.isPresent()) {
            presentations = this.presentationManager.getAllPresentations();
        } else if (start.isPresent() && end.isPresent() && !title.isPresent() && movie.isPresent()) {
            presentations = this.presentationManager.getAllPresentationsByMovie(start.get(), end.get(), movie.get());
        }

        if (presentations == null) {
            return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(presentations, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/presentations", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody Presentation presentation) {
        boolean result = this.presentationManager.persistPresentation(presentation);
        if (result) {
            return new ResponseEntity<>(presentation, HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable String id) {
        boolean result = false;
        if (id != null && !id.isEmpty()) {
            result = this.presentationManager.deletePresentation(id);
        }
        if (result) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }


}
