package de.dhbw.ics.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class PriceCategory {

    private String uuid;
    private PresentationCategory presentationCategory = null;
    private SeatCategory seatCategory = null;
    private String title;
    private String description;
    private BigDecimal price = null;

    @JsonCreator
    public PriceCategory(Map<String, Object> delegate) {
        if (delegate.get("uuid") == null) {
            this.uuid = UUID.randomUUID().toString();
        } else {
            this.uuid = (String) delegate.get("uuid");
        }

        this.title = (String) delegate.get("title");
        this.description = (String) delegate.get("description");
        if (delegate.get("presentationCategory") != null) {
            this.presentationCategory = new PresentationCategory((Map<String,Object>) delegate.get("presentationCategory"));
        }
        if (delegate.get("seatCategory") != null) {
            this.seatCategory = new SeatCategory((Map<String, Object>) delegate.get("seatCategory"));
        }
        if (delegate.get("price") != null) {
            this.price = new BigDecimal((String)delegate.get("price"));
        }
    }

    public PriceCategory(String uuid, PresentationCategory presentationCategory, SeatCategory seatCategory, String title, String description, BigDecimal price) {
        this.uuid = uuid;
        this.presentationCategory = presentationCategory;
        this.seatCategory = seatCategory;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public PriceCategory(PresentationCategory presentationCategory, SeatCategory seatCategory, String title, String description, BigDecimal price) {
        this.presentationCategory = presentationCategory;
        this.seatCategory = seatCategory;
        this.title = title;
        this.description = description;
        this.price = price;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public PresentationCategory getPresentationCategory() {
        return presentationCategory;
    }

    public void setPresentationCategory(PresentationCategory presentationCategory) {
        this.presentationCategory = presentationCategory;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PriceCategory that = (PriceCategory) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(presentationCategory, that.presentationCategory)
                .append(seatCategory, that.seatCategory)
                .append(title, that.title)
                .append(description, that.description)
                .append(price, that.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(presentationCategory)
                .append(seatCategory)
                .append(title)
                .append(description)
                .append(price)
                .toHashCode();
    }
}
