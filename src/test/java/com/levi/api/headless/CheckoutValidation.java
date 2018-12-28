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

public class CheckoutValidation {


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
	
	public CheckoutValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}

	public Response initiateCheckout(String generatedCartID, String emailID) {
		/*
		 * INITIATE CUSTOMER CHECKOUT
		 */
		JSONObject cartChildParam = new JSONObject();
		cartChildParam.put("cartId", generatedCartID); 
		cartChildParam.put("userId", emailID);
		System.out.println(cartChildParam);
		resp = given().body(cartChildParam).contentType(ContentType.JSON). 
				parameter("Authorization", "bearer "+BaseSetUp.generatedToken).expect().statusCode(200). 
				when().post(baseSetUp.CHECKOUT);
		return resp;


	}
	
	public Response proceedRegCheckout(String generatedCartID) {
		/*
		 * PROCEED TO CUSTOMER CHECKOUT
		 */
		
		resp = given().
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				parameter("Authorization", "bearer "+BaseSetUp.generatedToken).expect().statusCode(201). 
				when().post(baseSetUp.CHECKOUTREGORDER);
		return resp;


	}

}
