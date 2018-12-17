package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.PaymentMethod;
import de.dhbw.ics.vo.Role;
import de.dhbw.ics.vo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new RoleMapper().mapRow(resultSet, i);
        PaymentMethod paymentMethod = new PaymentMethodMapper().mapRow(resultSet,i);
        String uuid = resultSet.getString("user_uuid");
        String email = resultSet.getString("email");
        String password =  resultSet.getString("password");

        User user = new User(uuid,email,role,password);
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setPaymentMethod(paymentMethod);

        return user;
    }
}
