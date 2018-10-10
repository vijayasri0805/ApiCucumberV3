package com.levi.api.aoslse.steps;

import com.levi.api.aoslse.Promo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.Result;

import org.hamcrest.Matchers;
import org.testng.ITestResult;

public class AOSLSEPromoSteps {
	
	public Promo promo = new Promo();

	@Given("^initialize the Promo test suite$")
	public void initialize_the_Promo_test_suite() throws Throwable {
	   promo.setup();
	}

	@Then("^get the auth token for Promo$")
	public void get_the_auth_token_for_Promo() {
	    promo.authToken();
	}

	@Then("^create the cartID and Allocated Order number for Promo$")
	public void create_the_cartID_and_Allocated_Order_number_for_Promo() throws Throwable {
	    promo.createCartID();
	}

	@Then("^create the order using Promo$")
	public void create_the_order_using_Promo() throws Throwable {
	    promo.promo();
	}
	@And("^capture the status of the scenarios$")
	public void capture_the_status_of_the_scenarios() throws Throwable {
	    promo.captureStatus();
	}

	@Then("^flush the pervious results$")
	public void flush_the_pervious_results() throws Throwable {
	    promo.cleanup();
	}


}
