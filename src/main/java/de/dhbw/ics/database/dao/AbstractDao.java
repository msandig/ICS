package de.dhbw.ics.database.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    protected DataSource dataSource = null;
    protected JdbcTemplate jdbcTemplate = null;
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        if (dataSource != null) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    protected boolean persistObject(Class clazz, Object primaryKey, String countStatement, String updateStatement, String persistStatement, Object... args) {
        try {
            if (jdbcTemplate != null) {
                int i = jdbcTemplate.queryForObject(countStatement, Integer.class, primaryKey);
                if (i == 1) {
                    var helper = args[0];
                    args[0] = args[args.length-1];
                    args[args.length-1] = helper;
                    jdbcTemplate.update(updateStatement, args);
                } else {
                    jdbcTemplate.update(persistStatement, args);
                }
                return true;
            }
        } catch (DataAccessException e) {
            LOG.info("Could not persist {}!", clazz.getName() );
        }
        return false;
    }

    protected T getObject(Class clazz, String selectStatement, Object[] args , RowMapper rowMapper){
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject(selectStatement, args, rowMapper);
        } catch (DataAccessException e) {
            LOG.info("Could not find {} for primary key: {} ", clazz.getName(), args);
        }

        return (T) object;
    }

    protected boolean deleteObject(Class clazz, String deleteStatement, Object primaryKey){
        try {
            return (jdbcTemplate.update(deleteStatement, primaryKey) != 0) ? true : false;
        } catch (DataAccessException e) {
            LOG.info("Could not delete {} for primary key: {}", clazz.getName(), primaryKey);
        }
        return false;
    }

    protected List<T> getAllObjects(Class clazz, String selectStatement, RowMapper rowMapper){
        List<T> objectList = null;
        try {
            objectList = jdbcTemplate.query(selectStatement, rowMapper);
        } catch (DataAccessException e) {
            LOG.info("Could not find any object of kind {}!", clazz.getName());
        }

        return objectList;
    }

    protected List<T> getObjectsByMultipleArguments(Class clazz, String selectStatement, Object[] args , RowMapper rowMapper){
        List<T> objectList = null;
        try {
            objectList = jdbcTemplate.query(selectStatement, args, rowMapper);
        } catch (DataAccessException e) {
            LOG.info("Could not find any object of kind {}!", clazz.getName());
        }
        return objectList;
    }
}
