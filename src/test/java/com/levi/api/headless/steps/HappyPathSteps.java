package com.levi.api.headless.steps;

import com.levi.api.headless.HappyPathTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HappyPathSteps {
public HappyPathTest happy = new HappyPathTest();
	
	@Given("^initialize the test suite for Happy Path$")
	public void initialize_the_test_suite_for_Happy_Path() throws Throwable {
		happy.setup();
	}
	
	@Then("^get the token for HappyPath$")
	public void get_the_token_for_HappyPath() throws Throwable {
	    happy.authToken();
	}

	@And("^create cartID and GUID for Happy Path$")
	public void create_cartID_and_GUID_for_Happy_Path() throws Throwable {
	    happy.createCartID();
	}

	@When("^user places an order using Happy Path$")
	public void user_places_an_order_using_Happy_Path() {
	    happy.happy();
	}

	@Then("^capture the status of the Happy scenario$")
	public void capture_the_status_of_the_Happy_scenario() throws Throwable {
		happy.captureStatus();
	}

	@And("^clean up the pervious results of Happy Path$")
	public void clean_up_the_pervious_results_of_Happy_Path() throws Throwable {
	   happy.cleanup();
	}

}
