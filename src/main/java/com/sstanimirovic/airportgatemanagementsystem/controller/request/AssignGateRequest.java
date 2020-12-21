package com.sstanimirovic.airportgatemanagementsystem.controller.request;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.REGEX_FLIGHT_NUMBER;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_FLIGHT_NUMBER_INVALID;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_NUMBER_INVALID;

@ApiModel
public class AssignGateRequest {

    private String gateNumber;
    private String flightNumber;

    @Length(min=1, max = 10, message = MESSAGE_GATE_NUMBER_INVALID)
    @NotNull(message = MESSAGE_GATE_NUMBER_INVALID)
    public String getGateNumber() {
        return gateNumber;
    }

    @NotNull(message = MESSAGE_FLIGHT_NUMBER_INVALID)
    @Pattern(regexp = REGEX_FLIGHT_NUMBER, message = MESSAGE_FLIGHT_NUMBER_INVALID)
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
