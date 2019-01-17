package de.dhbw.ics.manager;

import de.dhbw.ics.controller.web.ResultMessage;
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
    private PresentationDao presentationDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private BusySeatDao busySeatDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private PriceCategoryDao priceCategoryDao;

    public Object getAllPriceCategories(){
        return this.priceCategoryDao.getAll();
    }

    public List<Presentation> getAllPresentations() {
        return this.presentationDao.getAll();
    }

    public List<Movie> getAllMovies() {
        return this.movieDao.getAll();
    }

    public Object getAllMoviesByTitle(String title) {
        if (title == null || title.isEmpty())
            return ResultMessage.MISSING_MOVIE_TITLE;

        return this.movieDao.getMovieByTitle(title);
    }

    public List<Presentation> getAllPresentations(long start, long end) {
        List<Presentation> presentations = this.presentationDao.getPresentationsBetweenInterval(start, end);
        this.mapRooms(presentations);
        return presentations;
    }

    public Object getAllPresentationsByMovie(long start, long end, String movieID) {
        if (movieID == null || movieID.isEmpty() || start == 0 || end == 0)
            return ResultMessage.WRONG_PARAMETERS;

        List<Presentation> presentations = this.presentationDao.getPresentationsByMovieAndDateInterval(start, end, movieID);
        this.mapRooms(presentations);
        for (Presentation presentation : presentations) {
            this.mapSeatInformation(presentation);
        }

        return presentations;
    }

    public Object getAllMoviesBetweenInterval(long start, long end){
        if(start == 0 || end == 0)
            return ResultMessage.WRONG_PARAMETERS;

        return this.movieDao.getMoviesBetweenDate(start, end);
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

    public Object persistPresentation(Presentation presentation) {
        if (presentation.getUuid().isEmpty())
            return ResultMessage.MISSING_PRESENTATION_ID;
        if (Objects.isNull(presentation.getRoom()) || presentation.getRoom().getUuid().isEmpty())
            return ResultMessage.MISSING_ROOM;

        if (Calendar.getInstance().getTimeInMillis() >= presentation.getDate())
            return ResultMessage.DATE_IN_PAST;

        if (Objects.isNull(presentation.getMovie()) || presentation.getMovie().getUuid().isEmpty())
            return ResultMessage.MISSING_MOVIE;

        if (Objects.isNull(presentation.getPresentationCategory()) || presentation.getPresentationCategory().getUuid().isEmpty())
            return ResultMessage.MISSING_PRESENTATION_CATEGORY;

        if(this.presentationDao.persist(presentation))
            return presentation;

        return ResultMessage.COULD_NOT_PERSIST_PRESENTATION;
    }

    public Object getPresentation(String uuid) {
        if(uuid == null || uuid.isEmpty())
            return ResultMessage.MISSING_PRESENTATION_ID;

        Presentation presentation = presentationDao.get(uuid);
        mapSeatInformation(presentation);
        return presentation;
    }

    public String deletePresentation(String uuid) {
        if(uuid == null || uuid.isEmpty())
            return ResultMessage.MISSING_PRESENTATION_ID;

        if(presentationDao.delete(uuid))
            return ResultMessage.SUCCESS;

        return ResultMessage.COULD_NOT_DELETE_PRESENTATION;
    }


    public List<Presentation> getAllPresentationsByTitle(long start, long end, String title) {
        List<Presentation> presentations = this.presentationDao.getPresentationsByTitleAndDateInterval(start, end, title);
        this.mapRooms(presentations);
        return presentations;
    }

}
