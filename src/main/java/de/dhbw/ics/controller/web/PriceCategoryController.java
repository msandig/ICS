package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.PresentationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceCategoryController {

    @Autowired
    private PresentationManager presentationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/categories/price")
    public @ResponseBody
    ResponseEntity<Object> getAll() {
        Object result = this.presentationManager.getAllPriceCategories();
        if (result instanceof List) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
