package de.dhbw.ics.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return new EqualsBuilder()
                .append(uuid, role.uuid)
                .append(title, role.title)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(title)
                .toHashCode();
    }
}
