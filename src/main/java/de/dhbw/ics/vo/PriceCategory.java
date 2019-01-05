package de.dhbw.ics.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.UUID;

public class PriceCategory {

    private String uuid = StringUtils.EMPTY;
    private PresentationCategory presentationCategory = null;
    private SeatCategory seatCategory = null;
    private String title = StringUtils.EMPTY;
    private String description = StringUtils.EMPTY;
    private BigDecimal price = null;

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
