package com.levi.api.aoslse;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.http.Method;
import com.jayway.restassured.response.Response;
import com.levi.api.utils.PropertyReader;
import com.levi.api.utils.TestDataUtils;
import com.levi.api.*;
import net.minidev.json.JSONObject;

public class RemoveCart {

	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;
	
	public ExtentTest testInfo;
	
	public Response resp;
	
	public String SCAN;
	
	public String CARTID;
	
	public String AUTHTOKEN;
	
	public String PRODUCTDATA;
	
	public String SWATCHDATA;
	
	public String SELECTEDPC13;
	
	public String ADDTOCART;
	
	public String VIEWCART;
	
	public String DELETECART;
	
	String generatedCartID;
	String generatedToken;
	String generatedUid;
	String selectedPC9;
	String SelectedPC13;
	String AddedCartValue;
	String QuantityAdded;
	String AllocatedOrderNumber;
	String ViewCartValue;
	
	PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
	List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();
	
	@BeforeTest
	public void setup() throws Exception {
		
		SCAN = reader.getData("scanBarCode");
		CARTID = reader.getData("createCartID");
		PRODUCTDATA = reader.getData("productData");
		SWATCHDATA = reader.getData("swatchData");
		SELECTEDPC13 = reader.getData("selectPC13");
		ADDTOCART = reader.getData("addToCart");
		VIEWCART = reader.getData("viewCart");
		DELETECART = reader.getData("deleteCart");
		AUTHTOKEN = reader.getData("authToken");
		
		testDataMap = TestDataUtils.readFromFileAndConvertToMap(new File("src/test/resource/testdata/AOSLSE.csv"));
		
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSERemoveCart.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "AOSLSE");
		report.attachReporter(htmlReporter);
		
		
	}
	
	@Test
	public void removeCart() {
		
		testInfo = report.createTest("Test Scenario : RemoveCart");
		for(Map<String,String> testData : testDataMap) {
		
		/*
		 * AUTH TOKEN
		 */
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "testaoswebclient"); 
		requestParams.put("password", "Test@0swebClient");
				
				resp = given().body(requestParams.toJSONString()). 
						expect().statusCode(200).contentType(ContentType.JSON).
						when().
						post(AUTHTOKEN);
				System.out.println("resp " + resp.asString());
		
		
		String idToken = resp. 
				then().extract().path("id_token");

		generatedToken=idToken;
		
		/*
		 * GUID AND ALLOCATED ORDER
		 */
		
		resp = given().
				contentType(ContentType.JSON).auth().oauth2(generatedToken).expect().statusCode(200).
				when().
				post(CARTID);
		
		
		String allocatedOrdNum = resp. 
				then(). 
				extract(). 
				path("allocatedOrderNumber");
		AllocatedOrderNumber=allocatedOrdNum;
		
		
		
		String cartID = resp. 
				then().extract().
				path("guid");
				
		System.out.println("GUID : " + cartID);
		generatedCartID=cartID;
		
		/*
		 * SCAN BARCODE EAN		
		 */
				resp = given().
						pathParam("EAN", testData.get("EAN")).
						expect().statusCode(200).contentType(ContentType.JSON).
						when().
						get(SCAN);
				
				String pc9 = resp.then().contentType(ContentType.JSON).extract().path("baseOptions[0].selected.code");
				
				selectedPC9=pc9;
				
		/*
		 * PRODUCTDATA
		 */
				resp = given().pathParam("PC9", selectedPC9).
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
						pathParam("SelectedPC9", selectedPC9). 
						expect().statusCode(200).contentType(ContentType.JSON).
						when().get(SWATCHDATA);
				
				String swatchAvailability = resp. 
						then().
						extract().
						path("swatchAvailabilities[5].variantsAvailability").toString();	
				
				
				
				String swatchSelectedProductCode = resp. 
						then(). 
						extract(). 
						path("swatchAvailabilities[5].code");
				
				System.out.println("Swatch Selected PC9 : " + swatchSelectedProductCode);
				
				/*
				 * SELECT PC13
				 */
				resp = given().pathParam("SelectedPC9", selectedPC9). 
						expect().statusCode(200).contentType(ContentType.JSON). 
						when().get(SELECTEDPC13);
				
				String pc13 = selectedPC9 + "0" + testData.get("L_W_S1");
				SelectedPC13 = pc13;
				
				System.out.println("Selected PC13 : " + pc13);
				
				/*
				 * ADD TO CART
				 */

				resp = given().body("code="+pc13+"&qty="+testData.get("Qty1")).
						pathParam("guid", generatedCartID).contentType(ContentType.JSON).
						expect().statusCode(200). 
						when().post(ADDTOCART);
				
				//System.out.println(resp.asString());
				
				String Statuscode = resp.then().extract().path("statusCode");
				System.out.println("Status Code for add to cart : " + Statuscode);
				
				String addedpc13 = resp.then().extract().path("entry.product.code");
				if(addedpc13.equals(pc13)) {
					assertEquals(addedpc13, pc13);
				}else {
					System.out.println("Slected PC13 is not equals to Added PC13");
				}
				
				String CartValue = resp.then().extract().path("entry.totalPrice.value").toString();
				AddedCartValue = CartValue;
				
				String addedQuantity = resp.then().extract().path("quantityAdded").toString();
				addedQuantity.equals(testData.get("Qty1"));
				
				/*
				 * VIEW CART
				 */
						
				resp = given().
						pathParameter("guid", generatedCartID).
						when().get(VIEWCART);
				
				String totalPriceWithTax = resp.then().extract().path("totalPriceWithTax.value").toString();
				if(totalPriceWithTax.equals(AddedCartValue)) {
					assertEquals(totalPriceWithTax, CartValue);
				}else {
					System.out.println("Actual cart value is not equal to view cart value");
				}
				ViewCartValue=totalPriceWithTax;
				 String viewCartGUID = resp.then().extract().path("guid");
				 if(viewCartGUID.equals(generatedCartID)) {
					 assertEquals(viewCartGUID, generatedCartID);
				 }else {
					 System.out.println("CartID and View Cart GUID is not equal");
				 }
		
				 /*
				  * DELETE CART
				  */
				 
				 resp = given().body("code="+SelectedPC13+"&qty=0").
						 pathParameter("guid", generatedCartID).
						 contentType(ContentType.JSON).auth().
						 oauth2(generatedToken).expect().statusCode(200). 
						 when().delete(DELETECART);
				 
				 /*
				  * VIEW EDIT CART
				  */
				
				 resp = given().
							pathParameter("guid", generatedCartID).
							when().get(VIEWCART);
					
					String totalPriceWithTax1 = resp.then().extract().path("totalPriceWithTax.value").toString();
					if(totalPriceWithTax1.equals(AddedCartValue)) {
						assertEquals(totalPriceWithTax1, CartValue);
					}else {
						System.out.println("Actual cart value is not equal to view cart value");
					}
					ViewCartValue=totalPriceWithTax1;
					 String viewCartGUID1 = resp.then().extract().path("guid");
					 if(viewCartGUID1.equals(generatedCartID)) {
						 assertEquals(viewCartGUID1, generatedCartID);
					 }else {
						 System.out.println("CartID and View Cart GUID is not equal");
					 }
		
					testInfo.log(Status.PASS, "Selected PC9 from Response is : " + selectedPC9);
					testInfo.log(Status.PASS, "Selected PC13 from Response is : " + SelectedPC13);
					testInfo.log(Status.PASS, "Acutal Price of the selected PC13 is : " + totalPriceWithTax);
					testInfo.log(Status.PASS, "Total price after the cart is removed : " + totalPriceWithTax1);
		
		}	
		
		
		
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
	
	
	
	


}
