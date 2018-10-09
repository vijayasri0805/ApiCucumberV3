package com.levi.api.aoslse.steps;

import org.testng.ITestResult;

import com.levi.api.aoslse.EditCart;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.formatter.model.Result;

public class AOSLSEEditCartSteps {
	
	public EditCart editCart = new EditCart();
	
	@Given("^initialize the test suite$")
	public void initialize_the_test_suite() throws Throwable {
	    editCart.setup();
	}
	
	@Then("^get the auth token for EditCart$")
	public void get_the_auth_token_for_EditCart(){
	    editCart.editAuthToken();
	}

	@And("^create the cartID and Allocated Order number for EditCart$")
	public void create_the_cartID_and_Allocated_Order_number_for_EditCart() {
	    editCart.editCartID();
	}

	@Then("^remove products from the cart$")
	public void remove_products_from_the_cart() {
	    editCart.editCart();
	}
	
	@And("^capture the status of the EditCart Scenario$")
	public void capture_the_status_of_the_EditCart_Scenario() throws Throwable {
	    editCart.captureStatus();
	}

	@Then("^flush the pervious results for the EditCart scenario$")
	public void flush_the_pervious_results_for_the_EditCart_scenario() throws Throwable {
	    editCart.cleanup();
	}

}
