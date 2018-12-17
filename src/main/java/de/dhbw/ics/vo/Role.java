package de.dhbw.ics.vo;

import java.util.UUID;

public class Role {

    private String uuid;
    private String title;

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Role(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }

    public Role(String title) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
    }

    public boolean isAdmin() {
        return this.title.equals("Admin");
    }
}
