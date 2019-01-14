package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class Genre {

    private String uuid;
    private String name;

    @JsonCreator
    public Genre(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }

        this.name = (String) delegate.get("name");
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return new EqualsBuilder()
                .append(uuid, genre.uuid)
                .append(name, genre.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(name)
                .toHashCode();
    }
}
