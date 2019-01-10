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

public class PLPValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;

	
	public BaseSetUp baseSetUp;
	
	public PLPValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}

	
	public Response getCategorySortedDetails(String category, String sortType) {		
		/*
		 * PRODUCT DATA		
		 */
		Response resp;
		resp = given().
				pathParam("sortType", sortType).contentType(ContentType.JSON).
				pathParam("category", category).contentType(ContentType.JSON).
				pathParam("queryData", "511 slim:relevance\\:productItemType:Jeans").contentType(ContentType.JSON).
				expect().statusCode(200).
				when().
				get(baseSetUp.CATEGORYSORT);
		return resp;
		

	}
	
	public Response validateCategoryData(String category) {		
		/*
		 * PRODUCT DATA		
		 */
		Response resp;
		resp = given().
				pathParam("category", category).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().
				get(baseSetUp.CATEGORYDETAILS);
		return resp;
		

	}
	
	public Response getCategoryBreadCrumbDetails(String category) {		
		/*
		 * PRODUCT DATA		
		 */
		Response resp;
		resp = given().
				pathParam("category", category).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().
				get(baseSetUp.CATEGORYPAGE);
		return resp;
		

	}
	

}
