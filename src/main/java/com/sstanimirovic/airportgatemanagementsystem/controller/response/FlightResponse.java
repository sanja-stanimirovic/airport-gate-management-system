package com.sstanimirovic.airportgatemanagementsystem.controller.response;

import com.sstanimirovic.airportgatemanagementsystem.repostory.model.Flight;
import io.swagger.annotations.ApiModel;

import java.util.Objects;

@ApiModel
public class FlightResponse {

    private String id;
    private String gateId;

    public FlightResponse() {
    }

    public FlightResponse(Flight f) {
        this.id = f.getId();
        if (Objects.nonNull(f.getGate())) {
            this.gateId = f.getGate().getId();
        }
    }

    public String getId() {
        return id;
    }

    public String getGateId() {
        return gateId;
    }
}
