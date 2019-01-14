package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.UserMapper;
import de.dhbw.ics.vo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UserDao extends AbstractDao<User> {

    private static final String PERSIST = "INSERT INTO USER (email, user_uuid, firstName, lastName, password, pay_uuid, role_uuid) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT USER.email as email, USER.user_uuid as user_uuid, USER.firstname as firstname, USER.lastname as lastname, " +
            "USER.password as password, USER.pay_uuid as pay_uuid, USER.role_uuid as role_uuid, PAYMENT_METHOD.provider as provider, " +
            "PAYMENT_METHOD.pay_description as pay_description, ROLE.role_title as role_title " +
            "FROM USER JOIN PAYMENT_METHOD ON USER.pay_uuid  = PAYMENT_METHOD.pay_uuid JOIN ROLE ON USER.role_uuid = ROLE.role_uuid WHERE email = ?";
    private static final String DELETE = "DELETE FROM USER WHERE email = ?";
    private static final String UPDATE = "UPDATE USER SET user_uuid = ?, firstName = ?, lastName = ?, password = ?, pay_uuid = ?, role_uuid = ? WHERE email = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM USER WHERE email = ?";
    private static final String SELECT_ALL = "SELECT USER.email as email, USER.user_uuid as user_uuid, USER.firstname as firstname, USER.lastname as lastname, " +
            "USER.password as password, USER.pay_uuid as pay_uuid, USER.role_uuid as role_uuid, PAYMENT_METHOD.provider as provider, " +
            "PAYMENT_METHOD.pay_description as pay_description, ROLE.role_title as role_title " +
            "FROM USER JOIN PAYMENT_METHOD ON USER.pay_uuid  = PAYMENT_METHOD.pay_uuid JOIN ROLE ON USER.role_uuid = ROLE.role_uuid";

    @Override
    public boolean persist(User object) {
        if (object != null) {
            if (object.getPaymentMethod() != null) {
                return this.persistObject(User.class, object.getEmail(), COUNT, UPDATE, PERSIST, object.getEmail(), object.getUuid(), object.getFirstName(), object.getLastName(), object.getPassword(), object.getPaymentMethod().getUuid(), object.getRole().getUuid());
            } else {
                return this.persistObject(User.class, object.getEmail(), COUNT, UPDATE, PERSIST, object.getEmail(), object.getUuid(), object.getFirstName(), object.getLastName(), object.getPassword(), null, object.getRole().getUuid());
            }
        }
        return false;
    }

    @Override
    public User get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(User.class, SELECT, new Object[]{key}, new UserMapper());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.getAllObjects(User.class, SELECT_ALL, new UserMapper());
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(User.class, DELETE, new Object[]{key});
        }
        return false;
    }
}
