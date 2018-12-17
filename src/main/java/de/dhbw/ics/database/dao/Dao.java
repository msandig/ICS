package de.dhbw.ics.database.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Dao<T> {

    void setDataSource(DataSource dataSource);

    boolean persist(T object);

    T get(Object key);

    boolean delete(Object key);

    List<T> getAll();

}
