package de.dhbw.ics.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Blob;
import java.util.UUID;

public class Movie {

    private String uuid;
    private Genre genre = null;
    private Integer productionYear;
    private String title;
    private Integer fsk;
    private Integer runTime;
    private Blob picture = null;
    private String description;

    public Movie(String uuid, Integer productionYear, String title, String description, Integer fsk, Integer runTime) {
        this.uuid = uuid;
        this.productionYear = productionYear;
        this.title = title;
        this.description = description;
        this.fsk = fsk;
        this.runTime = runTime;
    }

    public Movie(Integer productionYear, String title, String description, Integer fsk, Integer runTime) {
        this.productionYear = productionYear;
        this.title = title;
        this.description = description;
        this.fsk = fsk;
        this.runTime = runTime;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFsk() {
        return fsk;
    }

    public void setFsk(Integer fsk) {
        this.fsk = fsk;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
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

        Movie movie = (Movie) o;

        return new EqualsBuilder()
                .append(uuid, movie.uuid)
                .append(genre, movie.genre)
                .append(productionYear, movie.productionYear)
                .append(title, movie.title)
                .append(fsk, movie.fsk)
                .append(runTime, movie.runTime)
                .append(picture, movie.picture)
                .append(description, movie.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(genre)
                .append(productionYear)
                .append(title)
                .append(fsk)
                .append(runTime)
                .append(picture)
                .append(description)
                .toHashCode();
    }
}
