package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.UserMapper;
import de.dhbw.ics.vo.User;

import java.util.List;

public class UserDao extends AbstractDao<User> {

    private static final String PERSIST = "INSERT INTO USER (user_uuid, firstName, lastName, email, password, pay_uuid) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM USER JOIN PAYMENT_METHOD ON USER.pay_uuid  = PAYMENT_METHOD.pay_uuid JOIN ROLE ON USER.role_uuid = ROLE.role_uuid WHERE email = ?";
    private static final String DELETE = "DELETE FROM USER WHERE email = ?";
    private static final String UPDATE = "UPDATE USER SET firstName = ?, lastName = ?, password = ?, pay_uuid = ?, role_uuid = ? WHERE email = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM USER WHERE email = ?";
    private static final String SELECT_ALL = "SELECT * FROM USER JOIN PAYMENT_METHOD ON USER.pay_uuid  = PAYMENT_METHOD.pay_uuid JOIN ROLE ON USER.role_uuid = ROLE.role_uuid";

    @Override
    public boolean persist(User object) {
        if (object instanceof User && object != null) {
            User user = object;
            return this.persistObject(User.class, user.getEmail(), COUNT, UPDATE, PERSIST, user.getUuid(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getPaymentMethod().getUuid());
        }
        return false;
    }

    @Override
    public User get(Object key) {
        return this.getObject(User.class, SELECT, new Object[]{key}, new UserMapper());
    }

    @Override
    public List<User> getAll() {
        return this.getAllObjects(User.class, SELECT_ALL, new UserMapper());
    }

    @Override
    public boolean delete(Object key) {
        return this.deleteObject(User.class, DELETE, key);
    }
}
