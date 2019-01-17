package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.ReservationManager;
import de.dhbw.ics.vo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReservationController {

    @Autowired
    private ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/reservations/{id}")
    public @ResponseBody
    ResponseEntity<Object> get(@PathVariable Integer id, @RequestParam(value = "email") String email) {
        Object result = null;
        if (id != null && id != 0 && email != null && !email.isEmpty()) {
            result = this.reservationManager.getReservation(email, id);
        }
        if (result instanceof Reservation) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/reservations", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody Reservation reservation, HttpServletRequest request) {
        Object result = this.reservationManager.persistReservation(reservation, request.getSession().getId());
        if (result instanceof Reservation) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/service/get/reservations/{id}")
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable Integer id, @RequestParam(value = "email") String email) {
        Object result = null;
        if (id != null && id != 0 && email != null && !email.isEmpty()) {
            result = this.reservationManager.deleteReservation(email, id);
        }
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(ResultMessage.FAILED, HttpStatus.EXPECTATION_FAILED);
    }


}
