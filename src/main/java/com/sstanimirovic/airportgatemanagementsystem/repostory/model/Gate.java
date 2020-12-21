package com.sstanimirovic.airportgatemanagementsystem.repostory.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import java.util.Objects;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.REGEX_TIME;

@Entity
public class Gate {

    private String id;
    private Flight flight;
    private String openingHours;
    private String closingHours;

    @Id
    @Length(min = 1, max = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "gate", fetch = FetchType.LAZY)
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Pattern(regexp = REGEX_TIME)
    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Pattern(regexp = REGEX_TIME)
    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gate gate = (Gate) o;
        return id.equals(gate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
