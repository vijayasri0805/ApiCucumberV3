package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;




import java.util.HashMap;
import java.util.Map;

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

public class LoginValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;

	
	public BaseSetUp baseSetUp;
	
	public LoginValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}

	public Response authToken() {		
		/*
		 * AUTH TOKEN		
		 */
		Response resp;
		Map<String,String> params = new HashMap<String,String>();
		params.put("client_id", "headless_rest_client");
		params.put("client_secret", "Levis1234");
		params.put("grant_type", "client_credentials");

		resp = given().parameters(params).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().
				post(baseSetUp.AUTHTOKEN);
		return resp;
		

	}
	
	public Response regAuthToken() {		
		/*
		 * AUTH TOKEN		
		 */
		Response resp;
		Map<String,String> params = new HashMap<String,String>();
		params.put("client_id", "headless_rest_client");
		params.put("client_secret", "Levis1234");
		params.put("grant_type", "client_credentials");
		params.put("username", baseSetUp.UID);
		params.put("password", baseSetUp.PWD);

		resp = given().parameters(params).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().
				post(baseSetUp.AUTHTOKEN);
		return resp;
		

	}

}
