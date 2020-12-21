package com.sstanimirovic.airportgatemanagementsystem.service;

import com.sstanimirovic.airportgatemanagementsystem.repostory.FlightRepository;
import com.sstanimirovic.airportgatemanagementsystem.repostory.GateRepository;
import com.sstanimirovic.airportgatemanagementsystem.service.exception.GateServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
public class GateServiceTest {

    private GateService gateService;
    @MockBean
    private GateRepository gateRepository;
    @MockBean
    private FlightRepository flightRepository;

    @BeforeEach
    public void initGateService() {
        gateService = new GateServiceImpl(flightRepository, gateRepository);
    }

    @Test
    public void assignGateFlightExceptionTest() {

        String flightNumber = "BA1234";
        String gateNumber = "57-89";

        Mockito.when(flightRepository.findById(flightNumber)).thenReturn(null);

        try {
            gateService.assignGate(gateNumber, flightNumber);
            fail();
        } catch (GateServiceException e) {
            assertEquals("The flight is not found", e.getMessage());
        } catch (Exception e) {
            fail();
        }

    }

}
