package com.levi.api.cdhinv;



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

public class CDHInventoryPC9Test {
	
	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;
	
	public ExtentTest testInfo;
	
	public String expectedDisplayQuantity;
	
	public List<String> actualDisplayInventory;
	
	public List<String> actualInventoryStatus;
	
	public List<String> actualCode;
	
	private String CDH_URL_US;
	
	private String CDH_URL_CA;
	
	public Response resp;
	
	PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
	List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();

	@BeforeTest
	public void setup() throws Exception {
		CDH_URL_US = reader.getData("cdh-inventory-US-pc9");
		CDH_URL_CA = reader.getData("cdh-inventory-CA-pc9");
		testDataMap = TestDataUtils.readFromFileAndConvertToMap(new File("src/test/resource/testdata/cdhinventory.csv"));
		
			htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/CDHAPIAutomationReportPC9.html"));
			htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
			report=new ExtentReports();
			report.setSystemInfo("Environment", "QA");
			report.attachReporter(htmlReporter);
	}

	@Test
	public void checkPC9() {
		
		for(Map<String,String> testData : testDataMap) {
			testInfo = report.createTest("Test case : CheckPC9");
			String regionUS = testData.get("REGION");
			String regionCA = testData.get("REGION");
			if (regionUS.toLowerCase().contains("us"))
					{
				System.out.println(regionUS);
				 resp = given(). 
						pathParam("pc9", testData.get("PC9")).
						expect().statusCode(200).contentType(ContentType.JSON).
						when().
						get(CDH_URL_US);
			} else if (regionCA.toLowerCase().contains("canada")) {
				System.out.println(regionCA);
				 resp = given(). 
						pathParam("pc9", testData.get("PC9")).
						expect().statusCode(200).contentType(ContentType.JSON).
						when(). 			
						get(CDH_URL_CA);
			} else {
				System.out.println("Bad String");
			}
			expectedDisplayQuantity = testData.get("DISPLAY_QTY");
			System.out.println("The display quantity from the datasheet is : "+ expectedDisplayQuantity);
			//System.out.println(resp.asString());

			String responseMessage = resp.then().extract().path("message");
			System.out.println("Print the Message of the pc9 : " + responseMessage);
			
			List<String> codeOfThepc9 = resp.
					then().extract().path("inventory.code");
			System.out.println("Check the Inventory code : " + codeOfThepc9.get(0));

			List<String> typeOfProduct = resp.then().extract().path("inventory.variantType");
			System.out.println("Check the Variant Type of the Product : " + typeOfProduct);
			System.out.println("Test Inventory " + resp.path("inventory.displayInventory"));
			System.out.println("Test Status " + resp.path("inventory.inventoryStatus"));

			List<Integer> inventory=resp.path("inventory.displayInventory");
			List<String> stockStatus=resp.path("inventory.inventoryStatus");
			
			if(inventory.get(0)>20)
			{
				assertEquals(stockStatus.get(0), "In Stock");
				System.out.println(stockStatus.get(0));
			} else  if (inventory.get(0)<=20 && inventory.get(0)>=1){
				assertEquals(stockStatus.get(0), "Low Stock");
				System.out.println(stockStatus.get(0));
			} else if (inventory.get(0)<=0){
				assertEquals(stockStatus.get(0), "Out of Stock");
				System.out.println(stockStatus.get(0));
			} else {
				
				System.out.println("Invalid Response");
			}
		
			actualCode = resp.path("inventory.code");
			actualDisplayInventory = resp.path("inventory.displayInventory");
			actualInventoryStatus = resp.path("inventory.inventoryStatus");
			
			testInfo.log(Status.PASS, "The Actual PC9 Value from Response is : " + actualCode);
			testInfo.log(Status.PASS, "The Actual Dispaly Inventory Value from DataSheet is : " + actualDisplayInventory);
			testInfo.log(Status.PASS, "The Actual Inventory Status is : " + actualInventoryStatus);
			
		}
	}
		
	@AfterMethod
	public void captureStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testInfo.log(Status.PASS, "The Test Method named " + result.getName() + " is PASSED");		
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testInfo.log(Status.FAIL, "The Test Method named  " + result.getName() + " is FAILED");
			testInfo.log(Status.FAIL, "Test Failure : " + result.getThrowable());
			testInfo.log(Status.FAIL, "The Actual PC9 is : " + actualCode);
		} else if (result.getStatus() == ITestResult.SKIP) {
			testInfo.log(Status.SKIP, "The Test Method named :" + result.getAttributeNames() + " is SKIPPED");
		}
	}
	
	@AfterTest
	public void cleanup() {
		report.flush();
	}
}
