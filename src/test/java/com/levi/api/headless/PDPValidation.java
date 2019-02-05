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

public class PDPValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;

	
	public BaseSetUp baseSetUp;
	
	public PDPValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}

	
	public Response getProductDetails() {		
		/*
		 * PRODUCT DATA		
		 */
		Response resp;
		System.out.println("PRODUCTDATA: "+baseSetUp.PRODUCTDATA.replace("{PC9}",baseSetUp.PC9));
		resp = given().
				pathParam("PC9", baseSetUp.PC9).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().
				get(baseSetUp.PRODUCTDATA);
		return resp;
		

	}
	
	public Response validateSwatchData() {		
		/*
		 * SWATCH DATA		
		 */
		Response resp;
		System.out.println("SWATCHDATA: "+baseSetUp.SWATCHDATA.replace("{PC9}",baseSetUp.PC9));
		resp = given().
				pathParam("PC9", baseSetUp.PC9).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().
				get(baseSetUp.SWATCHDATA);
		
		return resp;
		

	}

}
