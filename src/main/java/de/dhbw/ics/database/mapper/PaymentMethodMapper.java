package de.dhbw.ics.database.mapper;

import de.dhbw.ics.vo.PaymentMethod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodMapper implements RowMapper<PaymentMethod> {

    @Override
    public PaymentMethod mapRow(ResultSet resultSet, int i) throws SQLException {
        String uuid = resultSet.getString("pay_uuid");
        String description = resultSet.getString("pay_description");
        String provider =  resultSet.getString("provider");
        PaymentMethod paymentMethod = new PaymentMethod(uuid, description, provider);
        return paymentMethod;
    }

}
