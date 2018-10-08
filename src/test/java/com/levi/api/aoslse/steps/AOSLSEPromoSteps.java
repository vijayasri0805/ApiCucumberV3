package com.levi.api.aoslse.steps;

import com.levi.api.aoslse.Promo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class AOSLSEPromoSteps {
	
	public Promo promo = new Promo();

	@Given("^get the auth token$")
	public void get_the_auth_token() {
		promo.authToken();
	}

	@And("^create the cartID and Allocated Order number$")
	public void create_the_cartID_and_Allocated_Order_number() throws Throwable {
	   promo.createCartID();
	}

	@Then("^place an order using AOSLSE promo\\.$")
	public void place_an_order_using_AOSLSE_promo() throws Throwable {
	    promo.promo();
	}


}
