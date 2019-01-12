package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.ReservationManager;
import de.dhbw.ics.vo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    @Autowired
    private ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/reservations/{id}")
    public @ResponseBody
    ResponseEntity<Reservation> get(@PathVariable Integer id, @RequestParam(value = "email") String email) {
        Reservation reservation = null;
        if (id != null && id != 0 && email != null && !email.isEmpty()) {
            reservation = this.reservationManager.getReservation(email, id);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/reservations", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody Reservation reservation) {
        Reservation result = this.reservationManager.persistReservation(reservation);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable Integer id, @RequestParam(value = "email") String email) {
        boolean result = false;
        if (id != null && id != 0 && email != null && !email.isEmpty()) {
            result = this.reservationManager.deleteReservation(email, id);
        }
        if (result) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }



}
