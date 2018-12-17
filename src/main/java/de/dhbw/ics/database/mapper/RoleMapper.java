package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {

        String uuid = resultSet.getString("role_uuid");
        String title = resultSet.getString("role_title");
        Role role = new Role(uuid, title);
        return role;
    }

}
