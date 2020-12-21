package com.sstanimirovic.airportgatemanagementsystem.service;

import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;

import java.util.List;

public interface GateService {

    Gate getAssignedGate(String flightNumber);

    void assignGate(String gateNumber, String flightNumber);

    void updateGateAvailability(String gateNumber, String openingTime, String closingTime);

    List<Flight> getAllFlights();

    List<Gate> getAvailableGates();
}
