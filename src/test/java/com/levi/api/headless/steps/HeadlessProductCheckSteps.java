package com.levi.api.headless.steps;


import com.levi.api.headless.CartValidation;

import cucumber.api.java.en.Given;


public class HeadlessProductCheckSteps {

	public CartValidation product = new CartValidation();
	
	@Given("^User checks the happy path$")
	public void initialize_the_Remove_Cart_test_suite() throws Throwable {
		product.checkProduct();
	}

	
}
