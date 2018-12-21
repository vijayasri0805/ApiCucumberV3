package com.levi.api.headless.steps;


import com.levi.api.headless.CartValidation;

import cucumber.api.java.en.Given;


public class CartValidationSteps {

	public CartValidation product = new CartValidation();
	
	@Given("^User adds product to cart for \"([^\"]*)\"$")
	public void add_product_to_cart(String locale) throws Throwable {
		product.addToCart(locale);
	}

	
}
