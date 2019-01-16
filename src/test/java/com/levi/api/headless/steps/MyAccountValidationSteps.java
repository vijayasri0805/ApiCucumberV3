package com.levi.api.headless.steps;

import static org.testng.Assert.assertEquals;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.LoginValidation;
import com.levi.api.headless.MyAccountValidation;

import cucumber.api.java.en.Given;



public class MyAccountValidationSteps {	

	Response resp;
	String addressID;
	
	@Given("^User pulls first non default shipping address for \"([^\"]*)\"$")
	public void pullSavedAddressID(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.getSavedAddress(BaseSetUp.generatedToken);
		addressID = resp.then().extract().path("addresses[1].id");
		
	}
	
	@Given("^User marks one address as default for \"([^\"]*)\"$")
	public void markSavedAddressDefault(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.markSavedAddressDefault(BaseSetUp.generatedToken, addressID);
		
		assertEquals(resp.getStatusCode(),200);
	}
	

	
}
