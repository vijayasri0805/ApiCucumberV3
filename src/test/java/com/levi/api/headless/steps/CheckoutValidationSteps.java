package com.levi.api.headless.steps;


import static org.testng.Assert.assertEquals;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.CartValidation;
import com.levi.api.headless.CheckoutValidation;
import com.levi.api.utils.CommonUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CheckoutValidationSteps {

	String AllocatedOrderNumber;
	String generatedUid;
	Response resp;
	
	@Given("^User initiates checkout process$")
	public void initiateCheckout(String locale) throws Throwable
	{/*
		CheckoutValidation checkOut = new CheckoutValidation(locale);
		resp = checkOut.initiateCheckout(generatedCartID, emailID);
		String uid = resp.then().extract().path("uid");
		generatedCartID=uid;
		System.out.println("UID:"+uid);
		*/
	}
	
	@Then("^User proceed to registered checkout for \"([^\"]*)\"$")
	public void proceedRegCheckout(String locale) throws Throwable
	{
		CheckoutValidation checkOut = new CheckoutValidation(locale);
		resp = checkOut.proceedRegCheckout(BaseSetUp.generatedCartID);
		String orderNumber = resp.then().extract().path("allocatedOrderNumber");
		
		System.out.println("orderNumber:"+orderNumber);
		
	}

	

	
}
