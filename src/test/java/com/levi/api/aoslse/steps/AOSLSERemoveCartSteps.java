package com.levi.api.aoslse.steps;

import com.levi.api.aoslse.RemoveCart;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class AOSLSERemoveCartSteps {

	public RemoveCart removeCart = new RemoveCart();
	
	@Given("^initialize the Remove Cart test suite$")
	public void initialize_the_Remove_Cart_test_suite() throws Throwable {
	    removeCart.setup();
	}

	@Then("^get the auth token for Remove Cart$")
	public void get_the_auth_token_for_Remove_Cart() throws Throwable {
		removeCart.getTokenRemoveCart();
	}

	@And("^create the cartID and Allocated Order number for Remove Cart$")
	public void create_the_cartID_and_Allocated_Order_number_for_Remove_Cart() throws Throwable {
	    removeCart.createCartIDRemoveCart();
	}

	@Then("^create the order using Remove Cart$")
	public void create_the_order_using_Remove_Cart() throws Throwable {
	    removeCart.removeCart();
	}

	@And("^capture the status of the Remove Cart scenarios$")
	public void capture_the_status_of_the_Remove_Cart_scenarios() throws Throwable {
	    removeCart.captureStatus();
	}

	@Then("^flush the pervious results for Remove Cart$")
	public void flush_the_pervious_results_for_Remove_Cart() throws Throwable {
	   removeCart.cleanup();
	}
}
