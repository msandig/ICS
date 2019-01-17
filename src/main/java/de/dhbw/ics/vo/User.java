package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User implements Cloneable {

    private String uuid;
    private String firstName = StringUtils.EMPTY;
    private String lastName = StringUtils.EMPTY;
    private String email;
    private String password;
    private PaymentMethod paymentMethod = null;
    private Role role = null;
    private List<Reservation> reservationList = new ArrayList<>();

    @JsonCreator
    public User(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }
        this.firstName = (String) delegate.get("firstName");
        this.lastName = (String) delegate.get("lastName");
        this.email = (String) delegate.get("email");
        this.setPassword((String) delegate.get("password"));
        if (delegate.get("paymentMethod") instanceof Map) {
            this.paymentMethod = new PaymentMethod((Map<String, Object>) delegate.get("paymentMethod"));
        }
        if (delegate.get("role") instanceof Map) {
            this.role = new Role((Map<String, Object>) delegate.get("role"));
        }

    }

    public User(String uuid) {
        this.uuid = uuid;
    }

    public User(String uuid, String email, Role role, String password) {
        this.uuid = uuid;
        this.email = email;
        this.role = role;
        this.setPassword(password);

    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.setPassword(password);
        this.role = role;
        this.uuid = UUID.randomUUID().toString();
    }

    private void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            return;
        }
        boolean isBase64 = Base64.isBase64(password);
        if (isBase64) {
            this.password = password;
        } else {
            this.password = Base64.encodeBase64String(password.getBytes());
        }
    }

    public Role getRole() {
        return role;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean comparePassword(String password) {
        return this.password.equals(Base64.encodeBase64String(password.getBytes()));
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(uuid, user.uuid)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(password, user.password)
                .append(paymentMethod, user.paymentMethod)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(password)
                .append(paymentMethod)
                .toHashCode();
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
