package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;

import net.minidev.json.JSONObject;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class MyAccountValidation {


	public ExtentHtmlReporter htmlReporter;
	public ExtentReports report;
	public ExtentTest testInfo;

	public Response resp;
	


	public BaseSetUp baseSetUp;
	
	public MyAccountValidation(String locale)
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


	
	public Response getSavedAddress(String generatedToken)
	{
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * GET SAVED ADDRESS
		 */
		resp = given().
				pathParam("UID", baseSetUp.UID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).
				when().get(baseSetUp.GETSAVEDADDR);

		return resp;

		
	}
	
	public Response markSavedAddressDefault(String generatedToken, String addressID)
	{
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * MARK SAVED ADDRESS DEFAULT
		 */

		/*JSONObject childBody = new JSONObject();
		childBody.put("code", SelectedPC13); 
		childBody.put("qty", baseSetUp.QTY);
		JSONObject mainBody = new JSONObject();
		mainBody.put("product", childBody);*/
		JSONObject body = new JSONObject();
		body.put("", ""); 
		
		resp = given().
				body(body).
				pathParam("UID", baseSetUp.UID).
				pathParam("ADDRID", addressID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).				
				when().patch(baseSetUp.MARKADDRDEFAULT);

		return resp;

		
	}
	
	public Response getSavedPayment(String generatedToken)
	{
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * GET SAVED ADDRESS
		 */
		resp = given().
				pathParam("UID", baseSetUp.UID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).
				when().get(baseSetUp.GETSAVEDPAYMENT);

		return resp;

		
	}
	
	public Response markSavedPaymentDefault(String generatedToken, String paymentID)
	{

		JSONObject body = new JSONObject();
		body.put("", ""); 
		
		resp = given().
				body(body).
				pathParam("UID", baseSetUp.UID).
				pathParam("PAYMENTID", paymentID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).				
				when().patch(baseSetUp.MARKPAYMENTDEFAULT);

		return resp;

		
	}
	
	public Response getListofConsents(String generatedToken)
	{
		JSONObject body = new JSONObject();
		body.put("", ""); 
		resp= given().
			body(body).
			pathParam("UID", baseSetUp.UID).
			header("Authorization", "bearer "+generatedToken).expect().statusCode(200)
			.when().get(baseSetUp.LISTOFCONSENTSDATA);
				
	return resp;
	}
	public Response geSpecificofConsents(String consentTemplateId,String generatedToken)
	{
		JSONObject body = new JSONObject();
		body.put("", ""); 
		resp= given().
			body(body).
			pathParam("UID", baseSetUp.UID).
			pathParam("consentTemplateId", consentTemplateId).
			header("Authorization", "bearer "+generatedToken).expect().statusCode(200)
			.when().get(baseSetUp.SPECIFICCONSENTDATA);		
	return resp;
	}
	public Response checkWithdrawnConsent(String consentTemplateId,String generatedToken)
	{
		JSONObject body = new JSONObject();
		body.put("", ""); 
		resp= given().
			body(body).
			pathParam("UID", baseSetUp.UID).
			pathParam("consentTemplateId", consentTemplateId).
			header("Authorization", "bearer "+generatedToken).expect().statusCode(200)
			.when().get(baseSetUp.SPECIFICCONSENTDATA);
				
	return resp;
	}
	public Response addConsent(String generatedToken)
	{
		/*JSONObject body = new JSONObject();

		body.put("", ""); */
		
		resp= given().
			//body(body).
			pathParam("UID", baseSetUp.UID).
			contentType(ContentType.URLENC).
			formParam("consentTemplateId",baseSetUp.CONSENTTEMPLATEID).
			formParam("consentTemplateVersion",baseSetUp.CONSENTTEMPLATEVERSION).
			header("Authorization", "bearer "+generatedToken).expect().statusCode(201)
			.when().post(baseSetUp.ADDCONSENT);
				
	return resp;
	}
	public Response deleteConsent(String consentId,String generatedToken){
		JSONObject body = new JSONObject();
		body.put("", ""); 
		resp= given().
			body(body).
			pathParam("UID", baseSetUp.UID).
			pathParam("consentCode", consentId).
			header("Authorization", "bearer "+generatedToken).expect().statusCode(200)
			.when().delete(baseSetUp.DELETECONSENTDATA);
				
	return resp;
	}
	@AfterMethod
	public void captureStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testInfo.log(Status.PASS, "The Test Method named " + result.getName() + " is PASSED");		
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testInfo.log(Status.FAIL, "The Test Method named  " + result.getName() + " is FAILED");
			testInfo.log(Status.FAIL, "Test Failure : " + result.getThrowable());
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
