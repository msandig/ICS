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

    @Override
    public boolean persist(Role object) {
        if(object instanceof Role && object != null) {
            Role role = object;
            return this.persistObject(Role.class, role.getUuid(), COUNT, UPDATE, PERSIST, role.getUuid(), role.getTitle());
        }
        return false;
    }

    @Override
    public Role get(Object key) {
        return this.getObject(Role.class, SELECT, new Object[]{key}, new RoleMapper());
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(Role.class, DELETE, key);
    }

    @Override
    public List<Role> getAll() {
        return this.getAllObjects(Role.class, SELECT_ALL, new RoleMapper());
    }
}
