package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class SeatCategory {

    private String uuid;
    private String title;
    private String description;

    public SeatCategory(SeatCategory seatCategory){
        this.uuid = seatCategory.getUuid();
        this.setTitle(seatCategory.getTitle());
        this.setDescription(seatCategory.getDescription());
    }

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


    @JsonCreator
    public SeatCategory(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }
        this.title = (String) delegate.get("title");
        this.description = (String) delegate.get("description");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SeatCategory that = (SeatCategory) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(title, that.title)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(title)
                .append(description)
                .toHashCode();
    }
}
