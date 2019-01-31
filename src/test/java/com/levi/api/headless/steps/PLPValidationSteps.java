package com.levi.api.headless.steps;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertTrue;

import java.io.File;

import com.cucumber.listener.Reporter;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.LoginValidation;
import com.levi.api.headless.PDPValidation;
import com.levi.api.headless.PLPValidation;

import cucumber.api.java.en.Given;



public class PLPValidationSteps {	

	Response resp;
	
	@Given("^User validates \"([^\"]*)\" detail with sort \"([^\"]*)\" for \"([^\"]*)\"$")
	public void validateSwatchData(String category, String sortType, String locale) throws Throwable
	{
		PLPValidation plp = new PLPValidation(locale);
		resp = plp.getCategorySortedDetails(category, sortType);
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/categorySortData.json"))));
		
		assertTrue(resp.then().extract().path("categoryCode").toString().contains(category));
		assertTrue(resp.then().extract().path("currentQuery.query.value").toString().contains(sortType));
		Reporter.addStepLog("Category wise sort is working fine.");
	}
	
	@Given("^User validates \"([^\"]*)\" detail for \"([^\"]*)\"$")
	public void validateCategoryData(String category, String locale) throws Throwable
	{
		PLPValidation plp = new PLPValidation(locale);
		resp = plp.validateCategoryData(category);
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/categoryService.json"))))
		.extract().path("categoryName").toString().matches("\\w+\\.?");
		
		Reporter.addStepLog("Category Details are showing properly");
	}
	
	@Given("^User validates breadcrumbs for \"([^\"]*)\" service for \"([^\"]*)\"$")
	public void validateBreadCrumbsForCategory(String category, String locale) throws Throwable
	{
		PLPValidation plp = new PLPValidation(locale);
		resp = plp.getCategoryBreadCrumbDetails(category);
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/categoryPage.json"))));
		
		assertTrue(resp.then().extract().path("lscoBreadcrumbs[0].name").toString().matches("\\w+\\.?"));
		
		Reporter.addStepLog("Breadcrumbs are showing properly");
	}

	
}
