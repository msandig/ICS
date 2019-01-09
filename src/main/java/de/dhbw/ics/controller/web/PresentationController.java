package de.dhbw.ics.controller.web;

import de.dhbw.ics.vo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PresentationController {

    @RequestMapping(method= RequestMethod.GET, path = "/service/get/presentation")
    public @ResponseBody
    ResponseEntity<Presentation> getMovie() {
        Movie movie = new Movie(2015, "TestMovie", "Nice Test Movie", 12, 120);
        Genre genre = new Genre("testGenre");
        movie.setGenre(genre);
        movie.setPicture("/movie/avatar.png");
        Room room = new Room("3D Raum", true, true, 1);
        Random ran = new Random();
        int x = ran.nextInt(6) + 10;
        int y = ran.nextInt(6) + 10;

        Presentation presentation = new Presentation(movie, room, new Date(), new PresentationCategory("3D","3D Vorstellung"));
        SeatCategory seatCategory = new SeatCategory("Kuschliger Platz", "besonders kuschlig");
        for(int i = 1; i < x; i++){
            for(int j = 1; j<= y; j++){
                Seat seat = new Seat(room, seatCategory,j, i);
                BusySeat busySeat = new BusySeat();
                busySeat.setSeat(seat);
                busySeat.setPresentation(presentation);
                busySeat.setLooked(false);
                busySeat.setBusy(false);
                seat.addBusy(busySeat);
                seat.setCurrentBusySeat(busySeat);
                room.getSeats().put(seat.getUuid(),seat);
            }
        }

        return new ResponseEntity<>(presentation, HttpStatus.OK);
    }

}
