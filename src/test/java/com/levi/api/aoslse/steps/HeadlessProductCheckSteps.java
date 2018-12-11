package com.levi.api.aoslse.steps;


import com.levi.api.aoslse.Product;
import cucumber.api.java.en.Given;


public class HeadlessProductCheckSteps {

	public Product product = new Product();
	
	@Given("^User checks the happy path$")
	public void initialize_the_Remove_Cart_test_suite() throws Throwable {
		product.checkProduct();
	}

	
}
