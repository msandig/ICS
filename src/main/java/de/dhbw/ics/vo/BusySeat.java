package de.dhbw.ics.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BusySeat {

    private boolean isBusy = false;
    private Seat seat = null;
    private Presentation presentation = null;
    private boolean looked = false;

    public boolean isLooked() {
        return looked;
    }

    public void setLooked(boolean looked) {
        this.looked = looked;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BusySeat busySeat = (BusySeat) o;

        return new EqualsBuilder()
                .append(isBusy, busySeat.isBusy)
                .append(looked, busySeat.looked)
                .append(seat, busySeat.seat)
                .append(presentation, busySeat.presentation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isBusy)
                .append(seat)
                .append(presentation)
                .append(looked)
                .toHashCode();
    }
}
