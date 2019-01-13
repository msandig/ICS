package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.RoleMapper;
import de.dhbw.ics.vo.Role;

import java.util.List;

public class RoleDao extends AbstractDao<Role> {

    private static final String PERSIST = "INSERT INTO ROLE (role_uuid, role_title) VALUES (?, ?)";
    private static final String SELECT = "SELECT * FROM ROLE WHERE role_uuid = ?";
    private static final String DELETE = "DELETE FROM ROLE WHERE role_uuid = ?";
    private static final String UPDATE = "UPDATE ROLE SET role_title = ? WHERE role_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM ROLE WHERE role_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM ROLE";
    private static final String SELECT_BY_TITLE = "SELECT * FROM ROLE WHERE role_title = ?";

    @Override
    public boolean persist(Role object) {
        if (object != null) {
            return this.persistObject(Role.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getTitle());
        }
        return false;
    }

    @Override
    public Role get(Object key) {
        return getRole(key, SELECT);
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(Role.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<Role> getAll() {
        return this.getAllObjects(Role.class, SELECT_ALL, new RoleMapper());
    }


    public Role getByTitle(Object key) {
        return getRole(key, SELECT_BY_TITLE);
    }

    private Role getRole(Object key, String selectByTitle) {
        if (key != null && !key.equals("")) {
            return this.getObject(Role.class, selectByTitle, new Object[]{key}, new RoleMapper());
        }
        return null;
    }
}
