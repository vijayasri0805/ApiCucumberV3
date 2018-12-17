package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.utils.PropertyReader;
import com.levi.api.utils.TestDataUtils;

import cucumber.api.java.Before;
import net.minidev.json.JSONObject;

public class ProductValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;

	public String PRODUCTDATA;

	public String SWATCHDATA;

	public String SELECTEDPC13;

	
	String generatedCartID;
	String generatedToken;
	String generatedUid;
	String selectedPC9;
	String SelectedPC13;
	String AddedCartValue;
	String QuantityAdded;
	
	PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
	List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();

	@Before
	public void setup() throws Exception {

		PRODUCTDATA = reader.getData("productData");
		SWATCHDATA = reader.getData("swatchData");
		SELECTEDPC13 = reader.getData("selectPC13");

		testDataMap = TestDataUtils.readFromFileAndConvertToMap(new File("src/test/resource/testdata/AOSLSE.csv"));

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}


	
		
	public void checkProduct()
	{
		
		//testInfo = report.createTest("Test Scenario : Product");


		resp = given().pathParam("PC9", "195870088").
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

		String maxOrderValue = resp.
				then().
				extract().
				path("maxOrderQuantity").toString();

		System.out.println("Max Order Quantity : " + maxOrderValue);

		String minOrderValue = resp.
				then().
				extract().
				path("minOrderQuantity").toString();

		System.out.println("Min Order Quantity : " + minOrderValue);

		/*
		 * SWATCH DATA
		 */

		resp = given(). 
				pathParam("SelectedPC9", "195870088"). 
				expect().statusCode(200).contentType(ContentType.JSON).
				when().get(SWATCHDATA);

		String swatchAvailability = resp. 
				then().
				extract().
				path("swatchAvailabilities[0].variantsAvailability").toString();	

		System.out.println("Swatch Availablity:"+swatchAvailability);

		/*String swatchSelectedProductCode = resp. 
				then(). 
				extract(). 
				path("swatchAvailabilities[0].code");*/

		/*
		 * SELECT PC13
		 */
		resp = given().pathParam("SelectedPC9", selectedPC9). 
				expect().statusCode(200).contentType(ContentType.JSON). 
				when().get(SELECTEDPC13);

		


	}


}
