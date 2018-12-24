package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;

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

import net.minidev.json.JSONObject;

public class CartValidation extends BaseSetUp{


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


	

	public void setup(String locale) throws Exception {

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}

	public Response createCartID(String generatedToken) {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		resp = given().
				parameter("Authorization", "bearer "+generatedToken).expect().statusCode(201).
				when().
				post(CARTID);

		return resp;

	}
	
	public String validatePC9()
	{
		/*
		 * PRODUCTDATA + Select PC13
		 */
		System.out.println("PRODUCTDATA:"+PRODUCTDATA);
		resp = given().pathParam("PC9", PC9).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().get(PRODUCTDATA);
		String itemPrice = resp. 
				then().
				extract().
				path("price.formattedValue");
		System.out.println("Price : " + itemPrice);

		String selectedpc9 = resp. 
				then(). 
				extract(). 
				path("code");

		System.out.println("Selected PC9 : " + selectedpc9);
		selectedPC9 = selectedpc9;
		System.out.println("SELECTEDPC13: "+SELECTEDPC13);
		resp = given().pathParam("SelectedPC9", selectedpc9). 
				expect().statusCode(200).contentType(ContentType.JSON). 
				when().get(SELECTEDPC13);
		String pc13 = selectedPC9 + "0" + SIZE;
		SelectedPC13 = pc13;

		return pc13;
		
	}

	public Response addToCart(String SelectedPC13, String generatedCartID)
	{

		
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * ADD TO CART
		 */

		JSONObject childParams = new JSONObject();
		childParams.put("code", SelectedPC13); 
		childParams.put("qty", QTY);
		JSONObject mainParams = new JSONObject();
		mainParams.put("product", childParams);

		resp = given().body(mainParams).
				pathParam("guid", generatedCartID).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().post(ADDTOCART);

		return resp;

		
	}
	
	public Response viewAndValidateCart(String generatedCartID)
	{/*
		 * VIEW CART
		 */

		resp = given().
				pathParameter("guid", generatedCartID).
				when().get(VIEWCART);
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
