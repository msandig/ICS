package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.UUID;

public class PresentationCategory {

    private String uuid;
    private String title;
    private String description;

    @JsonCreator
    public PresentationCategory(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }

        this.title = (String) delegate.get("title");
        this.description = (String) delegate.get("description");
    }

    public PresentationCategory(@JsonProperty("uuid")String uuid, @JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
    }


    public PresentationCategory(@JsonProperty("title") String title, @JsonProperty("description") String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PresentationCategory that = (PresentationCategory) o;

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
