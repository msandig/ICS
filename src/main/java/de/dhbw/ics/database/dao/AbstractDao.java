package de.dhbw.ics.database.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    protected JdbcTemplate jdbcTemplate = null;
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    @Override
    public void setDataSource(DataSource dataSource) {
        if (dataSource != null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    boolean persistObject(Class clazz, Object primaryKey, String countStatement, String updateStatement, String persistStatement, Object... args) {
        try {
            if (this.jdbcTemplate != null) {
                int i = this.jdbcTemplate.queryForObject(countStatement, Integer.class, primaryKey);
                if (i == 1) {
                    var helper = args[0];
                    Object[] updateArgs = new Object[args.length];
                    for (int j = 1; j <= updateArgs.length - 1; j++) {
                        updateArgs[j - 1] = args[j];
                    }
                    updateArgs[args.length - 1] = helper;
                    this.jdbcTemplate.update(updateStatement, updateArgs);
                    LOG.info("Update {}!", clazz.getSimpleName());
                } else {
                    this.jdbcTemplate.update(persistStatement, args);
                    LOG.info("Persist new {}!", clazz.getSimpleName());
                }
                return true;
            }
        } catch (DataAccessException e) {
            LOG.error("Could not persist {}!", clazz.getSimpleName());
            LOG.error(e.getMessage());

        }
        return false;
    }

    @SuppressWarnings("unchecked")
    T getObject(Class clazz, String selectStatement, Object[] args, RowMapper<T> rowMapper) {
        Object object = null;
        try {
            object = this.jdbcTemplate.queryForObject(selectStatement, args, rowMapper);
        } catch (DataAccessException e) {
            LOG.error("Could not find {} for primary key: {} ", clazz.getSimpleName(), args);
            LOG.error(e.getMessage());
        }

        return (T) object;
    }

    boolean deleteObject(Class clazz, String deleteStatement, Object[] primaryKey) {
        try {
            return jdbcTemplate.update(deleteStatement, primaryKey) != 0;
        } catch (DataAccessException e) {
            LOG.error("Could not delete {} for primary key: {}", clazz.getSimpleName(), primaryKey);
            LOG.error(e.getMessage());
        }
        return false;
    }

    List<T> getAllObjects(Class clazz, String selectStatement, RowMapper<T> rowMapper) {
        List<T> objectList = null;
        try {
            objectList = this.jdbcTemplate.query(selectStatement, rowMapper);
        } catch (DataAccessException e) {
            LOG.error("Could not find any object of kind {}!", clazz.getSimpleName());
            LOG.error(e.getMessage());
        }

        return objectList;
    }

    List<T> getObjectsByMultipleArguments(Class clazz, String selectStatement, Object[] args, RowMapper<T> rowMapper) {
        List<T> objectList = null;
        try {
            objectList = this.jdbcTemplate.query(selectStatement, args, rowMapper);
        } catch (DataAccessException e) {
            LOG.error("Could not find any object of kind {}!", clazz.getSimpleName());
            LOG.error(e.getMessage());
        }
        return objectList;
    }
}
