package com.levi.api.headless.steps;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertTrue;

import java.io.File;

import com.cucumber.listener.Reporter;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.LoginValidation;
import com.levi.api.headless.PDPValidation;

import cucumber.api.java.en.Given;



public class PDPValidationSteps {	

	Response resp;
	
	@Given("^User validates product detail for \"([^\"]*)\"$")
	public void validateProduct(String locale) throws Throwable
	{
		PDPValidation pdp = new PDPValidation(locale);
		resp = pdp.getProductDetails();
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/productDetail.json"))));
		assertTrue(resp.then().extract().path("code").toString().contains(pdp.baseSetUp.PC9));
		Reporter.addStepLog("Product Detail shown properly");
	}
	
	@Given("^User checks the swatch data for \"([^\"]*)\"$")
	public void validateSwatchData(String locale) throws Throwable
	{
		PDPValidation pdp = new PDPValidation(locale);
		resp = pdp.validateSwatchData();
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/swatchData.json"))));
		Reporter.addStepLog("Product Swatch shown properly");
	}

	
}
