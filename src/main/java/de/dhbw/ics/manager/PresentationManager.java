package de.dhbw.ics.manager;

import de.dhbw.ics.database.dao.GenreDao;
import de.dhbw.ics.database.dao.MovieDao;
import de.dhbw.ics.database.dao.PresentationDao;
import de.dhbw.ics.vo.Movie;
import de.dhbw.ics.vo.Presentation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class PresentationManager {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private GenreDao genreDao;

    public List<Presentation> getAllPresentations(){
        return this.presentationDao.getAll();
    }

    public List<Presentation> getAllPresentations(Date start, Date end){
        return this.presentationDao.getPresentationsBetweenIntervall(start, end);
    }

    public List<Movie> getAllMovie(int startDate, int endDate){
        return movieDao.getMoviesBetweenIntervall(startDate, endDate);
    }

    public boolean persistPresentation(Presentation presentation){
        return this.presentationDao.persist(presentation);
    }

    public Presentation getPresentation(String uuid){
        return presentationDao.get(uuid);
    }

    public boolean deletePresentation(String uuid){
        return presentationDao.delete(uuid);
    }


}
