package com.sstanimirovic.airportgatemanagementsystem.controller;

import com.sstanimirovic.airportgatemanagementsystem.controller.response.GateResponse;
import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;
import com.sstanimirovic.airportgatemanagementsystem.service.GateServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_FLIGHT_NUMBER_INVALID;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.REGEX_FLIGHT_NUMBER;

@Validated
@RestController
@RequestMapping("/gate")
@Api("Airport Gate Management System")
public class GateController {

    private final GateServiceImpl gateService;

    public GateController(GateServiceImpl gateService) {
        this.gateService = gateService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{flightNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GateResponse getAssignedGate(
            @PathVariable
            @Pattern(regexp = REGEX_FLIGHT_NUMBER, message = MESSAGE_FLIGHT_NUMBER_INVALID)
                    String flightNumber) {

        Gate assignedGate = gateService.getAssignedGate(flightNumber);

        return new GateResponse(assignedGate);
    }

}