package com.sstanimirovic.airportgatemanagementsystem.repostory;

import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GateRepository extends CrudRepository<Gate, String> {

    Gate findGateByFlightId(String flightNumber);

    Optional<Gate> findGateById(String gateNumber);

    List<Gate> findAllByFlight(Flight flight);

}
