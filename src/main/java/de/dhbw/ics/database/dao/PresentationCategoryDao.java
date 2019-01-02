package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.PresentationCategoryMapper;
import de.dhbw.ics.vo.PresentationCategory;

import java.util.List;

public class PresentationCategoryDao extends AbstractDao<PresentationCategory> {
    private static final String PERSIST = "INSERT INTO PRESENTATION_CATEGORY (prescat_uuid, prescat_title, prescat_description) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PRESENTATION_CATEGORY WHERE prescat_uuid = ?";
    private static final String DELETE = "DELETE FROM PRESENTATION_CATEGORY WHERE prescat_uuid = ?";
    private static final String UPDATE = "UPDATE PRESENTATION_CATEGORY SET prescat_title = ?, prescat_description = ? WHERE prescat_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM PRESENTATION_CATEGORY WHERE prescat_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM PRESENTATION_CATEGORY";


    @Override
    public boolean persist(PresentationCategory object) {
        if (object != null) {
            return this.persistObject(PresentationCategory.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getTitle(), object.getDescription());
        }
        return false;
    }

    @Override
    public PresentationCategory get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(PresentationCategory.class, SELECT, new Object[]{key}, new PresentationCategoryMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(PresentationCategory.class, DELETE, key);
        }
        return false;
    }

    @Override
    public List<PresentationCategory> getAll() {
        return this.getAllObjects(PresentationCategory.class, SELECT_ALL, new PresentationCategoryMapper());
    }
}
