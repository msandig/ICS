package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.GenreMapper;
import de.dhbw.ics.vo.Genre;

import java.util.List;

public class GenreDao extends AbstractDao<Genre> {

    private static final String PERSIST = "INSERT INTO GENRE (genre_uuid, name) VALUES (?, ?)";
    private static final String SELECT = "SELECT * FROM GENRE WHERE genre_uuid = ?";
    private static final String DELETE = "DELETE FROM GENRE WHERE genre_uuid = ?";
    private static final String UPDATE = "UPDATE GENRE SET name = ? WHERE genre_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM GENRE WHERE genre_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM GENRE";

    @Override
    public boolean persist(Genre object) {
        if (object != null) {
            return this.persistObject(Genre.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getName());
        }
        return false;
    }

    @Override
    public Genre get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(Genre.class, SELECT, new Object[]{key}, new GenreMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Genre.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Genre> getAll() {
        return this.getAllObjects(Genre.class, SELECT_ALL, new GenreMapper());
    }

}
