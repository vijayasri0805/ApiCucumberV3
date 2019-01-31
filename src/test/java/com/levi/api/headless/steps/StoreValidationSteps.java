package com.levi.api.headless.steps;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import com.cucumber.listener.Reporter;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.StoreValidation;

import cucumber.api.java.en.Then;


public class StoreValidationSteps {

	String SelectedPC13;
	String generatedUid;
	String AddedCartValue;
	String QuantityAdded;
	String AllocatedOrderNumber;
	String ViewCartValue;

	@Then("^User validates detail of BaseStore for \"([^\"]*)\"$")
	public void getBaseStoreDetails(String locale) throws Throwable
	{
		StoreValidation store = new StoreValidation(locale);
		Response resp = store.getBaseStoreDetails();
		
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/baseStore.json"))));
		
		assertTrue(resp.then().extract().path("country.isocode").toString().equals(store.baseSetUp.isocode));
		
		Reporter.addStepLog("Base store has all required details");
	}
	
	@Then("^User validates country detail of BaseStore for \"([^\"]*)\"$")
	public void getCountryDetails(String locale) throws Throwable
	{
		StoreValidation store = new StoreValidation(locale);
		Response resp = store.getCountryDetails();
		
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/country.json"))));
		
		Reporter.addStepLog("Country Details are shown properly");
	}
	
	@Then("^User validates region detail of BaseStore for \"([^\"]*)\"$")
	public void getRegionDetails(String locale) throws Throwable
	{
		StoreValidation store = new StoreValidation(locale);
		Response resp = store.getRegionDetails();
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/region.json"))));
		
		assertTrue(resp.then().extract().path("regions[0].isocode").toString().matches(".*"));
		Reporter.addStepLog("Region Details are shown properly");
	}
	
	
}
