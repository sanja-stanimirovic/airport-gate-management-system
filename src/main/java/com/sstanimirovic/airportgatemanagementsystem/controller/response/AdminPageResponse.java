package com.sstanimirovic.airportgatemanagementsystem.controller.response;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApiModel
public class AdminPageResponse {

    private List<FlightResponse> flights;
    private List<GateResponse> availableGates;

    public void addFlight(FlightResponse flight) {
        if (Objects.isNull(flights)) {
            flights = new ArrayList<>();
        }
        flights.add(flight);
    }

    public void addGate(GateResponse gate) {
        if (Objects.isNull(availableGates)) {
            availableGates = new ArrayList<>();
        }
        availableGates.add(gate);
    }

    public List<FlightResponse> getFlights() {
        return flights;
    }

    public List<GateResponse> getAvailableGates() {
        return availableGates;
    }
}
