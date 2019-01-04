package de.dhbw.ics.database.dao;

import de.dhbw.ics.database.mapper.PaymentMethodMapper;
import de.dhbw.ics.vo.PaymentMethod;

import java.util.List;

public class PaymentMethodDao extends AbstractDao<PaymentMethod> {

    private static final String PERSIST = "INSERT INTO PAYMENT_METHOD (pay_uuid, pay_description, provider) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PAYMENT_METHOD WHERE pay_uuid = ?";
    private static final String DELETE = "DELETE FROM PAYMENT_METHOD WHERE pay_uuid = ?";
    private static final String UPDATE = "UPDATE PAYMENT_METHOD SET pay_description = ?,provider = ? WHERE pay_uuid = ?";
    private static final String COUNT = "SELECT COUNT(*) FROM PAYMENT_METHOD WHERE pay_uuid = ?";
    private static final String SELECT_ALL = "SELECT * FROM PAYMENT_METHOD";


    @Override
    public boolean persist(PaymentMethod object) {
        if (object != null) {
            return this.persistObject(PaymentMethod.class, object.getUuid(), COUNT, UPDATE, PERSIST, object.getUuid(), object.getDescription(), object.getProvider());
        }
        return false;
    }

    @Override
    public PaymentMethod get(Object key) {
        if (key != null && !key.equals("")) {
            return this.getObject(PaymentMethod.class, SELECT, new Object[]{key}, new PaymentMethodMapper());
        }
        return null;
    }

    @Override
    public boolean delete(Object key) {
        if (key != null && !key.equals("")) {
            return this.deleteObject(PaymentMethod.class, DELETE,  new Object[]{key});
        }
        return false;
    }

    @Override
    public List<PaymentMethod> getAll() {
        return this.getAllObjects(PaymentMethod.class, SELECT_ALL, new PaymentMethodMapper());
    }
}
