package de.dhbw.ics.vo;

import java.util.UUID;

public class Genre {

    private String uuid;
    private String name;

    public Genre(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
