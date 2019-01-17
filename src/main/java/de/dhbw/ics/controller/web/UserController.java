package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.ReservationManager;
import de.dhbw.ics.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {


    @Autowired
    private ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/users/{email}")
    public @ResponseBody
    ResponseEntity<Object> get(@PathVariable String email, HttpServletRequest request) {
        Object result = null;
        if (email != null && !email.isEmpty()) {
            String password = request.getHeader("auth");
            result = this.reservationManager.getUser(email, password);
        }
        if (result instanceof User) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/users", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody User user) {
        Object result = null;
        if (user != null) {
            result = this.reservationManager.persistUser(user);
        }
        if (result instanceof User) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
