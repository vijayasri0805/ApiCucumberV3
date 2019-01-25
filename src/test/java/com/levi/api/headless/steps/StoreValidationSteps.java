package com.levi.api.headless.steps;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import java.io.File;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.CartValidation;
import com.levi.api.headless.StoreValidation;

import cucumber.api.java.en.And;
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
		
	}
	
	@Then("^User validates country detail of BaseStore for \"([^\"]*)\"$")
	public void getCountryDetails(String locale) throws Throwable
	{
		StoreValidation store = new StoreValidation(locale);
		Response resp = store.getCountryDetails();
		
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/country.json"))));
		
	}
	
	@Then("^User validates region detail of BaseStore for \"([^\"]*)\"$")
	public void getRegionDetails(String locale) throws Throwable
	{
		StoreValidation store = new StoreValidation(locale);
		Response resp = store.getRegionDetails();
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/region.json"))));
		
	}
	
	
}
