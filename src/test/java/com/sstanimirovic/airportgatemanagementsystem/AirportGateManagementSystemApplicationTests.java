package com.sstanimirovic.airportgatemanagementsystem;

import com.sstanimirovic.airportgatemanagementsystem.controller.response.AdminPageResponse;
import com.sstanimirovic.airportgatemanagementsystem.controller.response.GateResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AirportGateManagementSystemApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"spring.security.user.name=admin",
		"spring.security.user.password=password",
		"spring.security.user.roles=ADMIN"})
class AirportGateManagementSystemApplicationTests {

	@Autowired
	private TestRestTemplate template;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAssignedGateTest() {
		String flightNumber = "AA1234";

		ResponseEntity<GateResponse> result = template
				.getForEntity("/gate/" + flightNumber, GateResponse.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());

		GateResponse body = result.getBody();

		assertEquals("00:00:00", body.getOpeningHours().toString());
		assertEquals("23:59:59", body.getClosingHours().toString());
		assertEquals("CC-CC", body.getGateNumber());
	}

	@Test
	public void getAllFlightsAndFreeGatesUnauthorizedTest() {

		ResponseEntity<AdminPageResponse> result = template
				.getForEntity("/admin", AdminPageResponse.class);
		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

}
