package com.levi.api.headless.steps;


import com.levi.api.headless.ProductCheck;

import cucumber.api.java.en.Given;


public class HeadlessProductCheckSteps {

	public ProductCheck product = new ProductCheck();
	
	@Given("^User checks the happy path$")
	public void initialize_the_Remove_Cart_test_suite() throws Throwable {
		product.checkProduct();
	}

	
}
