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
        if(object instanceof Genre && object != null) {
            Genre genre = object;
            return this.persistObject(Genre.class, genre.getUuid(), COUNT, UPDATE, PERSIST, genre.getUuid(), genre.getName());
        }
        return false;
    }

    @Override
    public Genre get(Object key) {
        return this.getObject(Genre.class, SELECT, new Object[]{key}, new GenreMapper());
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(Genre.class, DELETE, key);
    }

    @Override
    public List<Genre> getAll() {
        return this.getAllObjects(Genre.class, SELECT_ALL, new GenreMapper());
    }

}
