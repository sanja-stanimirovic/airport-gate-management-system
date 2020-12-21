package com.sstanimirovic.airportgatemanagementsystem.service;

import com.sstanimirovic.airportgatemanagementsystem.repostory.FlightRepository;
import com.sstanimirovic.airportgatemanagementsystem.repostory.GateRepository;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;
import com.sstanimirovic.airportgatemanagementsystem.service.exception.GateServiceException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_ALREADY_ASSIGNED;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_FLIGHT_NOT_FOUND;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_NOT_ASSIGNED;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_NOT_FOUND;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_OCCUPIED;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_UNAVAILABLE;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_WORKING_HOURS_INVALID;

@Service
public class GateServiceImpl implements GateService {

    private final FlightRepository flightRepository;
    private final GateRepository gateRepository;

    public GateServiceImpl(FlightRepository flightRepository, GateRepository gateRepository) {
        this.flightRepository = flightRepository;
        this.gateRepository = gateRepository;
    }

    public Gate getAssignedGate(String flightNumber) {
        Gate gate = gateRepository.findGateByFlightId(flightNumber);

        if (Objects.isNull(gate)) {
            throw new GateServiceException(MESSAGE_GATE_NOT_ASSIGNED);
        }
        return gate;
    }

    public void assignGate(String gateNumber, String flightNumber) {

        Flight flight = flightRepository.findFlightById(flightNumber)
                .orElseThrow(() -> new GateServiceException(MESSAGE_FLIGHT_NOT_FOUND));

        Gate gate = gateRepository.findGateById(gateNumber)
                .orElseThrow(() -> new GateServiceException(MESSAGE_GATE_NOT_FOUND));

        validateGateAvailability(gate, flightNumber);

        flight.setGate(gate);
        gate.setFlight(flight);

        flightRepository.save(flight);
    }

    private void validateGateAvailability(Gate gate, String flightNumber) {
        if (Objects.nonNull(gate.getFlight())) {
            if (gate.getFlight().getId().equals(flightNumber)) {
                throw new GateServiceException(MESSAGE_ALREADY_ASSIGNED);
            }
            throw new GateServiceException(MESSAGE_GATE_OCCUPIED);
        }

        Integer now = Integer.parseInt((new Time(System.currentTimeMillis()).toString()).replace(":", ""));
        Integer openingTime = Integer.valueOf(gate.getOpeningHours().replace(":", ""));
        Integer closingTime = Integer.valueOf(gate.getClosingHours().replace(":", ""));

        if ( (openingTime - now) > 0 || (closingTime - now) < 0 ) {
            throw new GateServiceException(MESSAGE_GATE_UNAVAILABLE);
        }
    }

    public void updateGateAvailability(String gateNumber, String openingHours, String closingHours) {
        int openingTime = Integer.parseInt(openingHours.replace(":", ""));
        int closingTime = Integer.parseInt(closingHours.replace(":", ""));

        if (openingTime >= closingTime) {
            throw new GateServiceException(MESSAGE_WORKING_HOURS_INVALID);
        }

        Gate gate = gateRepository.findGateById(gateNumber)
                .orElseThrow(() -> new GateServiceException(MESSAGE_GATE_NOT_FOUND));

        gate.setOpeningHours(openingHours);
        gate.setClosingHours(closingHours);

        gateRepository.save(gate);

    }

    public List<Flight> getAllFlights() {
        Iterable<Flight> all = flightRepository.findAll();
        List<Flight> flights = new ArrayList<>();
        all.forEach(flights::add);
        return flights;
    }

    public List<Gate> getAvailableGates() {
        return gateRepository.findAllByFlight(null);
    }
}
