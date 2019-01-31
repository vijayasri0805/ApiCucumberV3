package com.levi.api.headless.steps;

import com.cucumber.listener.Reporter;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.LoginValidation;

import cucumber.api.java.en.Given;



public class LoginValidationSteps {	

	Response resp;
	
	@Given("^User generates anon auth token for \"([^\"]*)\"$")
	public void generateAnonAuth(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.authToken();
		BaseSetUp.generatedAnonToken = resp.then().extract().path("access_token");
	}
	
	@Given("^User generates registered auth token for \"([^\"]*)\"$")
	public void generateRegAuth(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.authToken();
		BaseSetUp.generatedToken = resp.then().extract().path("access_token");
		Reporter.addStepLog("User Login Successful with token " + BaseSetUp.generatedToken);
	}
	
	@Given("^User generates registered auth token of savedaddress user for \"([^\"]*)\"$")
	public void generateRegAuthforSavedPayment(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.regAuthToken(login.baseSetUp.SAVEDPAYUID, login.baseSetUp.PWD);
		BaseSetUp.generatedTokenSavedPayment = resp.then().extract().path("access_token");
		Reporter.addStepLog("User Login Successful with token " + BaseSetUp.generatedTokenSavedPayment);
	}
	
	@Given("^User creates registered user with optin for \"([^\"]*)\"$")
	public void createRegUserWithOptIn(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.createRegUserWithOptIn();
	}
	

	
}
