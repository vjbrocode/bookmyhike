package com.booking.app.integrationtest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestTrailViewUseCases extends SpringIntegrationTest {

	/**
	 * View All Trails Scenario
	 */
	@When("the client calls GET all trail URL /api/hike/v1/trails$")
	public void the_client_issues_GET_all_trails() throws Throwable {
		executeGet("http://localhost:8080/api/hike/v1/trails");
	}

	@Then("the client receives status code for fetch all trail {int}")
	public void the_client_receives_status_code_GET_all_trails(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives all trails json response {string}")
	public void the_client_receives_all_trails_json_response(String responseJson) {
		assertThat(latestResponse.getBody(), is(responseJson));
	}

	/**
	 * View One Trail Scenario
	 */
	@When("the client calls GET one trail URL /api/hike/v1/trails/(\\d+)$")
	public void the_client_issues_GET_one_trail(Integer id) throws Throwable {
		executeGet("http://localhost:8080/api/hike/v1/trails/" + id);
	}

	@Then("the client receives status code fetch one trail {int}")
	public void the_client_receives_status_code_GET_one_trail(Integer statusCode) throws IOException {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@Then("the client receives one trail json response {string}")
	public void the_client_receives_one_trail_json_response(String responseJson) {
		// String s =
		// "{\"id\":1,\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"09:00\",\"minimumAge\":5,\"maximumAge\":100,\"unitPrice\":29.9,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/api/hike/v1/trails/1\"},\"trails\":{\"href\":\"http://localhost:8080/api/hike/v1/trails\"}}}";
		assertThat(latestResponse.getBody(), is(responseJson));
	}

}
