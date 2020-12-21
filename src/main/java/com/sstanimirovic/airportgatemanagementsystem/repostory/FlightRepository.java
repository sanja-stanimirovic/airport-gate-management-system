package com.sstanimirovic.airportgatemanagementsystem.repostory;

import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FlightRepository extends CrudRepository<Flight, String> {

    Optional<Flight> findFlightById(String flightNumber);

}
