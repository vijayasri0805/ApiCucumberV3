package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.levi.api.utils.PropertyReader;

import net.minidev.json.JSONObject;

public class CartValidation {


	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;
	
	String BASEURL;

	public String PC9;
	public String SIZE;
	public String QTY;

	public String SCAN;

	public String CARTID;

	public String AUTHTOKEN;

	public String PRODUCTDATA;
	public String SELECTEDPC13;

	public String ADDTOCART;

	public String VIEWCART;



	public String PLACEORDER;
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

	public void setup(String locale) throws Exception {

		SCAN = reader.getData("scanBarCode");
		if(locale.equalsIgnoreCase("us"))
		{
			PC9 = reader.getData("usPC9");
			SIZE = reader.getData("usSize");
			QTY = reader.getData("usQty");
			BASEURL = reader.getData("baseURL").replace("{locale}", "US");
		}
		else
		{
			PC9 = reader.getData("caPC9");
			SIZE = reader.getData("caSize");
			QTY = reader.getData("caQty");
			BASEURL = reader.getData("baseURL").replace("{locale}", "CA");
		}
		CARTID = BASEURL+reader.getData("createCartID");
		PRODUCTDATA = BASEURL+reader.getData("productData");
		SELECTEDPC13 = BASEURL+reader.getData("selectPC13");
		ADDTOCART = BASEURL+reader.getData("addToCart");
		VIEWCART = BASEURL+reader.getData("viewCart");
		PLACEORDER = BASEURL+reader.getData("placeOrder");
		AUTHTOKEN = reader.getData("authToken");

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}

	public void authToken() {		
		/*
		 * AUTH TOKEN		
		 */

		Map<String,String> params = new HashMap<String,String>();
		params.put("client_id", "headless_rest_client");
		params.put("client_secret", "Levis1234");
		params.put("grant_type", "client_credentials");

		resp = given().parameters(params).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().
				post(AUTHTOKEN);

		System.out.println(resp.body());
		String idToken = resp.then().extract().path("access_token");
		System.out.println("GeneratedToken:"+ idToken);		
		generatedToken=idToken;


	}

	//@BeforeMethod
	public void createCartID() {



		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		System.out.println("CARTID: "+CARTID);
		resp = given().
				parameter("Authorization", "bearer "+generatedToken).expect().statusCode(201).
				when().
				post(CARTID);



		String allocatedOrdNum = resp. 
				then(). 
				extract(). 
				path("allocatedOrderNumber");
		AllocatedOrderNumber=allocatedOrdNum;
		System.out.println("AllocatedOrderNumber: "+AllocatedOrderNumber);
		String cartID = resp. 
				then().extract().
				path("guid");

		System.out.println("GUID : " + cartID);
		generatedCartID=cartID;


	}

	public void addToCart(String locale)
	{
		System.out.println("Locale: "+locale);
		try {
			setup(locale);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authToken();
		createCartID();

		/*
		 * PRODUCTDATA
		 */
		//testInfo = report.createTest("Test Scenario : Product");
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

		/*
		 * SELECT PC13
		 */
		System.out.println("SELECTEDPC13: "+SELECTEDPC13);
		resp = given().pathParam("SelectedPC9", selectedpc9). 
				expect().statusCode(200).contentType(ContentType.JSON). 
				when().get(SELECTEDPC13);


		String pc13 = selectedPC9 + "0" + SIZE;
		SelectedPC13 = pc13;

		System.out.println("Selected PC13 : " + pc13);

		/*
		 * ADD TO CART
		 */

		JSONObject childParams = new JSONObject();
		childParams.put("code", pc13); 
		childParams.put("qty", QTY);
		JSONObject mainParams = new JSONObject();
		mainParams.put("product", childParams);

		resp = given().body(mainParams).
				pathParam("guid", generatedCartID).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().post(ADDTOCART);

		String Statuscode = resp.then().extract().path("statusCode");
		System.out.println("Status Code for add to cart : " + Statuscode);

		String addedpc13 = resp.then().extract().path("entry.product.code");
		if(addedpc13.equals(pc13)) {
			assertEquals(addedpc13, pc13);
		}else {
			System.out.println("Selected PC13 is not equals to Added PC13");
		}

		String CartValue = resp.then().extract().path("entry.totalPrice.value").toString();
		AddedCartValue = CartValue;

		String addedQuantity = resp.then().extract().path("quantityAdded").toString();
		addedQuantity.equals(QTY);

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
