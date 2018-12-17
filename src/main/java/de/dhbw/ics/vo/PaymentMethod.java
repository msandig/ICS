package de.dhbw.ics.vo;

import java.util.UUID;

public class PaymentMethod {

    private String uuid;
    private String description;
    private String provider;

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
}
