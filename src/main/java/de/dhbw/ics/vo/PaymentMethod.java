package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class PaymentMethod {

    private String uuid;
    private String description;
    private String provider;

    @JsonCreator
    public PaymentMethod(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }

        this.description = (String) delegate.get("description");
        this.provider = (String) delegate.get("provider");

    }
    public PaymentMethod(String uuid, String description, String provider) {
        this.uuid = uuid;
        this.description = description;
        this.provider = provider;
    }

    public PaymentMethod(String description, String provider) {
        this.description = description;
        this.provider = provider;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PaymentMethod that = (PaymentMethod) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(description, that.description)
                .append(provider, that.provider)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(description)
                .append(provider)
                .toHashCode();
    }
}
