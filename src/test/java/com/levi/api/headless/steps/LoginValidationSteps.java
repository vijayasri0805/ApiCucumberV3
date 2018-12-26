package com.levi.api.headless.steps;


import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.CartValidation;
import com.levi.api.headless.CheckoutValidation;
import com.levi.api.headless.LoginValidation;
import com.levi.api.utils.CommonUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class LoginValidationSteps {	

	Response resp;
	
	@Given("^User generates auth token for \"([^\"]*)\"$")
	public void initiateCheckout(String locale) throws Throwable
	{
		LoginValidation login = new LoginValidation(locale);
		resp = login.authToken();
		System.out.println(resp.body());
		BaseSetUp.generatedToken = resp.then().extract().path("access_token");
	}
	

	
}
