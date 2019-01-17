package de.dhbw.ics.controller.web;

import de.dhbw.ics.manager.ReservationManager;
import de.dhbw.ics.vo.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BusySeatController {

    @Autowired
    ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/seats/lock", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> lockSeats(@RequestParam(value = "presentation") String presentation, @RequestParam(value = "locked") boolean locked, @RequestBody List<Seat> seats, HttpServletRequest request) {

        String sessionID = request.getSession().getId();
        Object result = null;
        if(presentation != null && !presentation.isEmpty()){
           result = reservationManager.lockSeats(presentation,seats, sessionID, locked);
        }

        if(result instanceof List) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
        }
    }


}
