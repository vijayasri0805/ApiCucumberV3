package com.levi.api.headless.steps;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.LoginValidation;
import cucumber.api.java.en.Given;



public class LoginValidationSteps {	

	Response resp;
	
	@Given("^User generates auth token for \"([^\"]*)\"$")
	public void generateAnonAuth(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.authToken();
		BaseSetUp.generatedToken = resp.then().extract().path("access_token");
	}
	
	@Given("^User generates registered auth token for \"([^\"]*)\"$")
	public void generateRegAuth(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.authToken();
		BaseSetUp.generatedToken = resp.then().extract().path("access_token");
		System.out.println("generatedToken:"+BaseSetUp.generatedToken);
	}
	

	
}
