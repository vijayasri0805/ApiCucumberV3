package com.levi.api.headless.steps;


import com.levi.api.headless.CartValidation;
import com.levi.api.headless.HappyPathValidation;

import cucumber.api.java.en.Given;


	public class HeadlessHappyPathSteps {


	@Given("^User checks the happy path$")
		public void initialize_the_Remove_Cart_test_suite() throws Throwable {

		HappyPathValidation hptest = new HappyPathValidation();
		hptest.happy();
	}

	
}
