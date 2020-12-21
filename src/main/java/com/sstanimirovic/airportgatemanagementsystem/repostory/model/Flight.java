package com.sstanimirovic.airportgatemanagementsystem.repostory.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import java.util.Objects;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.REGEX_FLIGHT_NUMBER;

@Entity
public class Flight {

    private String id;
    private Gate gate;

    @Id
    @Pattern(regexp = REGEX_FLIGHT_NUMBER)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gate_id", referencedColumnName = "id")
    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate assignedGate) {
        this.gate = assignedGate;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Flight flight = (Flight) object;
        return id.equals(flight.id);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
