package com.sstanimirovic.airportgatemanagementsystem.controller.response;

import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Gate;
import io.swagger.annotations.ApiModel;

@ApiModel
public class GateResponse {

    private String gateNumber;
    private String openingHours;
    private String closingHours;

    public GateResponse() {
    }

    public GateResponse(Gate gate) {
        this.gateNumber = gate.getId();
        this.openingHours = gate.getOpeningHours();
        this.closingHours = gate.getClosingHours();
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }
}
