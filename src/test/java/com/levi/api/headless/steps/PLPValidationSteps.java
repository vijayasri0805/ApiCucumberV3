package com.levi.api.headless.steps;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;

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
	}
	
	@Given("^User validates breadcrumbs for \"([^\"]*)\" service for \"([^\"]*)\"$")
	public void validateBreadCrumbsForCategory(String category, String locale) throws Throwable
	{
		PLPValidation plp = new PLPValidation(locale);
		resp = plp.getCategoryBreadCrumbDetails(category);
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/categoryPage.json"))));
	}

	
}
