package com.levi.api.aoslse.steps;

import com.levi.api.aoslse.EditCart;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class AOSLSEEditCartSteps {
	
	public EditCart editCart = new EditCart();
	
	@Given("^get the auth token for EditCart$")
	public void get_the_auth_token() {
		editCart.editAuthToken();
	}

	@And("^create the cartID and Allocated Order number for EditCart$")
	public void create_the_cartID_and_Allocated_Order_number() throws Throwable {
	   editCart.editCartID();
	}
	
	
	@Then("^remove products from the cart$")
	public void remove_products_from_the_cart() throws Throwable {
	   editCart.editCart();
	}

}
