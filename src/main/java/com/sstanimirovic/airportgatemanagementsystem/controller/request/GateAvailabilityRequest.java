package com.sstanimirovic.airportgatemanagementsystem.controller.request;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_GATE_NUMBER_INVALID;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_TIME_FORMAT_INVALID;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.REGEX_TIME;

@ApiModel
public class GateAvailabilityRequest {

    private String gateNumber;
    private String openingTime;
    private String closingTime;

    @Length(min=1, max = 10, message = MESSAGE_GATE_NUMBER_INVALID)
    @NotNull(message = MESSAGE_GATE_NUMBER_INVALID)
    public String getGateNumber() {
        return gateNumber;
    }

    @Pattern(regexp = REGEX_TIME, message = MESSAGE_TIME_FORMAT_INVALID)
    public String getOpeningTime() {
        return openingTime;
    }

    @Pattern(regexp = REGEX_TIME, message = MESSAGE_TIME_FORMAT_INVALID)
    public String getClosingTime() {
        return closingTime;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}
