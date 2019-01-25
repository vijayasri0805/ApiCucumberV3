package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;




import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import net.minidev.json.JSONObject;

public class StoreValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;
	

	String generatedUid;
	String selectedPC9;
	String SelectedPC13;
	String AddedCartValue;
	String QuantityAdded;
	String AllocatedOrderNumber;
	String ViewCartValue;

	public BaseSetUp baseSetUp;
	
	public StoreValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}
	

	public void setup(String locale) throws Exception {

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}

	public Response getBaseStoreDetails() {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		System.out.println(baseSetUp.BASESTOREDETAILS.replace("baseStoreUid", baseSetUp.baseStoreID));
		Response resp = given().
				pathParam("baseStoreUid", baseSetUp.baseStoreID).
				expect().statusCode(200).
				when().
				get(baseSetUp.BASESTOREDETAILS);

		return resp;

	}
	
	public Response getCountryDetails() {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		Response resp = given().
				expect().statusCode(200).
				when().
				get(baseSetUp.COUNTRYDETAILS);

		return resp;

	}
	
	public Response getRegionDetails() {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		Response resp = given().
				pathParam("countyIsoCode",baseSetUp.isocode).
				//expect().statusCode(200).
				when().
				get(baseSetUp.REGIONDETAILS);
		return resp;

	}
	
	


	@AfterMethod
	public void captureStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testInfo.log(Status.PASS, "The Test Method named " + result.getName() + " is PASSED");		
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testInfo.log(Status.FAIL, "The Test Method named  " + result.getName() + " is FAILED");
			testInfo.log(Status.FAIL, "Test Failure : " + result.getThrowable());
			testInfo.log(Status.FAIL, "The Actual PC9 is : " + selectedPC9);
		} else if (result.getStatus() == ITestResult.SKIP) {
			testInfo.log(Status.SKIP, "The Test Method named :" + result.getAttributeNames() + " is SKIPPED");
		}
	}

	@AfterTest
	public void cleanup() {
		report.flush();
	}

	public void captureStatus() {
		// TODO Auto-generated method stub

	}







}
