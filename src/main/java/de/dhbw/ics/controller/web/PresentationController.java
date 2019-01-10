package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
import de.dhbw.ics.vo.Presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    ResponseEntity<List<Presentation>> getAll(@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        List<Presentation> presentations = null;
        if(start != null && end != null){
            presentations = this.presentationManager.getAllPresentations();
        }else {
            presentations = this.presentationManager.getAllPresentations();
        }

        return new ResponseEntity<List<Presentation>>(presentations, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/presentations")
    public @ResponseBody
    ResponseEntity<Presentation> post(@RequestBody Presentation presentation) {
        boolean result = this.presentationManager.persistPresentation(presentation);
        if(result){
            return new ResponseEntity<>(presentation, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
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
