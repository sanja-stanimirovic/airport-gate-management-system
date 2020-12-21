package com.sstanimirovic.airportgatemanagementsystem.controller;

import com.sstanimirovic.airportgatemanagementsystem.controller.request.AssignGateRequest;
import com.sstanimirovic.airportgatemanagementsystem.controller.request.GateAvailabilityRequest;
import com.sstanimirovic.airportgatemanagementsystem.controller.response.AdminPageResponse;
import com.sstanimirovic.airportgatemanagementsystem.controller.response.FlightResponse;
import com.sstanimirovic.airportgatemanagementsystem.controller.response.GateResponse;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;
import com.sstanimirovic.airportgatemanagementsystem.service.GateServiceImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api("Admin Panel for Airport Gate Management System")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final GateServiceImpl gateService;

    public AdminController(GateServiceImpl gateService) {
        this.gateService = gateService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminPageResponse getAllFlightsAndFreeGates() {

        List<Flight> flights = gateService.getAllFlights();
        List<Gate> availableGates = gateService.getAvailableGates();

        AdminPageResponse response = new AdminPageResponse();

        flights.forEach(flight -> response.addFlight(new FlightResponse(flight)));
        availableGates.forEach(gate -> response.addGate(new GateResponse(gate)));

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/assign")
    public void assignGate(@Validated AssignGateRequest request) {
        gateService.assignGate(request.getGateNumber(), request.getFlightNumber());
        logger.info(String.format("The gate %s is assigned to flight %s",
                request.getGateNumber(), request.getFlightNumber()));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/update")
    public void updateGateAvailability(@Validated GateAvailabilityRequest request) {

        gateService.updateGateAvailability(request.getGateNumber(),
                request.getOpeningTime(), request.getClosingTime());
        logger.info(String.format("The opening and closing hours of the gate %s are updated to %s and %s",
                request.getGateNumber(), request.getOpeningTime().toString(), request.getClosingTime().toString()));
    }
}
