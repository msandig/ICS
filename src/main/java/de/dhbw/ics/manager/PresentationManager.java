package de.dhbw.ics.manager;

import de.dhbw.ics.database.dao.BusySeatDao;
import de.dhbw.ics.database.dao.MovieDao;
import de.dhbw.ics.database.dao.PresentationDao;
import de.dhbw.ics.database.dao.SeatDao;
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
    private PresentationDao presentationDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private BusySeatDao busySeatDao;

    @Autowired
    private MovieDao movieDao;

    public List<Presentation> getAllPresentations() {
        return this.presentationDao.getAll();
    }

    public List<Movie> getAllMovies() {
        return this.movieDao.getAll();
    }

    public List<Movie> getAllMoviesByTitle(String title) {
        if (title == null || title.isEmpty())
            return null;

        return this.movieDao.getMovieByTitle(title);
    }

    public List<Presentation> getAllPresentations(long start, long end) {
        List<Presentation> presentations = this.presentationDao.getPresentationsBetweenInterval(start, end);
        this.mapRooms(presentations);
        return presentations;
    }

    public List<Presentation> getAllPresentationsByMovie(long start, long end, String movieID) {
        if (movieID == null || movieID.isEmpty() || start == 0 || end == 0)
            return null;

        List<Presentation> presentations = this.presentationDao.getPresentationsByMovieAndDateInterval(start, end, movieID);
        this.mapRooms(presentations);
        for (Presentation presentation : presentations) {
            this.mapSeatInformation(presentation);
        }

        return presentations;
    }

    private void mapRooms(List<Presentation> presentations) {
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
        if (presentation.getUuid().isEmpty())
            return false;
        if (Objects.isNull(presentation.getRoom()) || presentation.getRoom().getUuid().isEmpty())
            return false;

        if (Calendar.getInstance().getTimeInMillis() >= presentation.getDate())
            return false;

        if (Objects.isNull(presentation.getMovie()) || presentation.getMovie().getUuid().isEmpty())
            return false;

        if (Objects.isNull(presentation.getPresentationCategory()) || presentation.getPresentationCategory().getUuid().isEmpty())
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


    public List<Presentation> getAllPresentationsByTitle(long start, long end, String title) {
        List<Presentation> presentations = this.presentationDao.getPresentationsByTitleAndDateInterval(start, end, title);
        this.mapRooms(presentations);
        return presentations;
    }

}
