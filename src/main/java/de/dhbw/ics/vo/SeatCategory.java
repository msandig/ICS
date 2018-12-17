package de.dhbw.ics.vo;

import java.util.UUID;

public class SeatCategory {

    private String uuid;
    private String title;
    private String description;

    public SeatCategory(String uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
    }

    public SeatCategory(String title, String description) {
        this.title = title;
        this.description = description;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
