package de.dhbw.ics.manager;

import de.dhbw.ics.database.dao.*;
import de.dhbw.ics.vo.Movie;
import de.dhbw.ics.vo.Presentation;
import de.dhbw.ics.vo.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class PresentationManager {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private BusySeatDao busySeatDao;

    public List<Presentation> getAllPresentations() {
        return this.presentationDao.getAll();
    }

    public List<Presentation> getAllPresentations(long start, long end) {
        List<Presentation> presentations = this.presentationDao.getPresentationsBetweenIntervall(start, end);
        this.mapRooms(presentations);
        return presentations;
    }

    public List<Movie> getAllMovie(int startDate, int endDate) {
        return this.movieDao.getMoviesBetweenIntervall(startDate, endDate);
    }

    private void mapRooms(List<Presentation> presentations){
        if (presentations != null) {
            List<Room> roomList = new ArrayList<>();
            presentations.forEach(p -> {
                if (p.getRoom() != null) {
                    if (!roomList.contains(p.getRoom())) {
                        roomList.add(p.getRoom());
                    } else {
                        p.setRoom(roomList.get(roomList.indexOf(p.getRoom())));
                    }
                }
            });
        }
    }

    private void mapSeatInformation(Presentation presentation) {
        if (presentation != null) {
            seatDao.getAllByRoom(presentation.getRoom());
            busySeatDao.getAllByPresentation(presentation);
        }

    }

    public boolean persistPresentation(Presentation presentation) {
        if(presentation.getUuid().isEmpty())
            return false;
        if(Objects.isNull(presentation.getRoom()) || presentation.getRoom().getUuid().isEmpty())
            return false;

        if(Calendar.getInstance().getTimeInMillis() >= presentation.getDate())
            return false;

        if(Objects.isNull(presentation.getMovie()) || presentation.getMovie().getUuid().isEmpty())
            return false;

        if(Objects.isNull(presentation.getPresentationCategory()) || presentation.getPresentationCategory().getUuid().isEmpty())
            return false;

        return this.presentationDao.persist(presentation);
    }

    public Presentation getPresentation(String uuid) {
        Presentation presentation = presentationDao.get(uuid);
        mapSeatInformation(presentation);
        return presentation;
    }

    public boolean deletePresentation(String uuid) {
        return presentationDao.delete(uuid);
    }


}
