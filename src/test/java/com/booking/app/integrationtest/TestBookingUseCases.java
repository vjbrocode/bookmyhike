package com.booking.app.integrationtest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestBookingUseCases extends SpringIntegrationTest {

	/**
	 * Get All Booking Scenario
	 */
	@When("the client calls GET all bookings URL /api/hike/v1/bookings$")
	public void the_client_issues_GET_all_bookings() throws Throwable {
		executeGet("http://localhost:8080/api/hike/v1/bookings");
	}

	@Then("the client receives status code for fetch all bookings {int}")
	public void the_client_receives_status_code_GET_all_bookings(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives all bookings json response {string}")
	public void the_client_receives_all_bookings_json_response(String responseJson) {
		assertTrue(latestResponse.getBody().startsWith(responseJson.substring(0, 58)));
	}

	/**
	 * Get One Booking Scenario
	 */
	@When("the client calls GET one booking URL /api/hike/v1/bookings/(\\d+)$")
	public void the_client_issues_GET_one_booking(Integer id) throws Throwable {
		executeGet("http://localhost:8080/api/hike/v1/bookings/" + id);
	}

	@Then("the client receives status code fetch one booking {int}")
	public void the_client_receives_status_code_GET_one_booking(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives one booking json response {string}")
	public void the_client_receives_one_trail_json_response(String responseJson) {
		assertTrue(latestResponse.getBody().startsWith(responseJson.substring(0, 30)));
	}

	/**
	 * New Booking Scenario
	 */
	@When("the client calls POST new booking URL /api/hike/v1/booktrail with request payload (.*)$")
	public void the_client_issues_POST_new_booking_request(String requestPayload) throws Throwable {
		executePost("http://localhost:8080/api/hike/v1/booktrail", cleanupValueToGetValidJsonString(requestPayload));
	}

	@Then("the client receives status code for new booking {int}")
	public void the_client_receives_status_code_POST_new_booking(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives new booking response {string}")
	public void the_client_receives_new_booking_json_response(String responseJson) {
		assertTrue(latestResponse.getBody().startsWith(responseJson.substring(0, 30)));
		assertTrue(latestResponse.getBody().contains("SUCCESS"));
	}

	/**
	 * Cancel Booking Scenario
	 */
	@When("the client calls PUT cancel booking URL /api/hike/v1/cancelBooking/(\\d+)$")
	public void the_client_issues_PUT_cancel_booking(Integer id) throws Throwable {
		executePut("http://localhost:8080/api/hike/v1/cancelBooking/" + id);
	}

	@Then("the client receives status code for cancel booking {int}")
	public void the_client_receives_status_code_PUT_cancel_booking(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives cancel booking response {string}")
	public void the_client_receives_cancel_booking_json_response(String responseJson) {
		assertTrue(latestResponse.getBody().startsWith(responseJson.substring(0, 30)));
		assertTrue(latestResponse.getBody().contains("CANCELLED"));
	}
}
