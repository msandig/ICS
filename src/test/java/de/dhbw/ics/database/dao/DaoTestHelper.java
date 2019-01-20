package de.dhbw.ics.database.dao;

import java.util.List;

import static org.junit.Assert.*;

public class DaoTestHelper {

    public static void persist(Dao dao, Object object) {
        assertTrue(dao.persist(object));
    }

    public static void get(Dao dao, Object testObject ,Object key) {
        Object o = dao.get(key);
        assertNotNull(o);
        assertEquals(testObject, o);
    }

    public static void getAll(Dao dao, Object testObject) {
        List<Object> objectList = dao.getAll();
        assertNotNull(objectList);
        assertNotEquals(objectList.size(), 0);
        Object o = objectList.get(objectList.indexOf(testObject));
        assertEquals(testObject, o);
    }

    public static void update(Dao dao, Object key, Object testObject, Object changedObject) {
        assertTrue(dao.persist(changedObject));
        Object o = dao.get(key);
        assertNotEquals(testObject, o);
        assertEquals(o, changedObject);
    }

    public static void delete(Dao dao, Object key) {
        assertTrue(dao.delete(key));
        assertNull(dao.get(key));
    }
}
