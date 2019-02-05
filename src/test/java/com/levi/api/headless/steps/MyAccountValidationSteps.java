package com.levi.api.headless.steps;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.MyAccountValidation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;



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
	
	@Given("^User pulls first non default payment method for \"([^\"]*)\"$")
	public void pullSavedPaymentID(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.getSavedPayment(BaseSetUp.generatedToken);
		addressID = resp.then().extract().path("payments[1].id");
		
	}
	
	@Given("^User marks one payment as default for \"([^\"]*)\"$")
	public void markSavedPaymentDefault(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.markSavedPaymentDefault(BaseSetUp.generatedToken, addressID);
		
		assertEquals(resp.getStatusCode(),200);
	}
	
	@Given("^User pulls list of consents used by Customer \"([^\"]*)\"$")
	public void getListofConsents(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.getListofConsents(BaseSetUp.generatedToken);
		resp.then().body(matchesJsonSchema(new File("src/test/resource/json-schema/getListofConsents.json")));
		assertEquals(resp.getStatusCode(),200);
	}
	@Then("^User pulls specific consent \"([^\"]*)\" for customer in \"([^\"]*)\"$")
	public void geSpecificofConsents(String consentTemplateId,String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.geSpecificofConsents(consentTemplateId,BaseSetUp.generatedToken);
		resp.then().body(matchesJsonSchema(new File("src/test/resource/json-schema/addConsents.json")));
		assertEquals(resp.getStatusCode(),200);
		String id = resp. 
				then().
				extract().
				path("id");
		assertEquals(id,consentTemplateId);

	}
	@Given("^User adds consents for \"([^\"]*)\"$")
	public void addConsent(String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.addConsent(BaseSetUp.generatedToken);
		resp.then().body(matchesJsonSchema(new File("src/test/resource/json-schema/addConsents.json")));
		assertEquals(resp.getStatusCode(),201);
	}
	@Then("^User Delete consent \"([^\"]*)\" for customer in \"([^\"]*)\"$")
	public void deleteConsent(String consentTemplateId,String locale) throws Throwable
	{
		MyAccountValidation myAccount = new MyAccountValidation(locale);
		resp = myAccount.geSpecificofConsents(consentTemplateId,BaseSetUp.generatedToken);
		assertEquals(resp.getStatusCode(),200);
		String consentId = resp. 
				then().
				extract().
				path("consentData.code");
		resp = myAccount.deleteConsent(consentId,BaseSetUp.generatedToken);
		assertEquals(resp.getStatusCode(),200);
		resp = myAccount.geSpecificofConsents(consentTemplateId,BaseSetUp.generatedToken);
		assertEquals(resp.getStatusCode(),200);
		String consentWithdrawnDate = resp. 
				then().
				extract().
				path("consentData.consentWithdrawnDate").toString();
		
	}
	
	
}
