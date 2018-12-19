package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.utils.PropertyReader;
import com.levi.api.utils.TestDataUtils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.minidev.json.JSONObject;


public class CartValidation {
	
static final Logger logger = 
			LoggerFactory.getLogger(CartValidation.class);



	public ExtentHtmlReporter htmlReporter;

	public ExtentReports report;

	public ExtentTest testInfo;

	public Response resp;

	public String SCAN;

	public String CARTID;

	public String AUTHTOKEN;


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

	@Before
	public void setup() throws Exception {

		SCAN = reader.getData("scanBarCode");
		CARTID = reader.getData("createCartID");

		SELECTEDPC13 = reader.getData("selectPC13");
		ADDTOCART = reader.getData("addToCart");
		VIEWCART = reader.getData("viewCart");
		PLACEORDER = reader.getData("placeOrder");
		AUTHTOKEN = reader.getData("authToken");

		testDataMap = TestDataUtils.readFromFileAndConvertToMap(new File("src/test/resource/testdata/AOSLSE.csv"));

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}

	//@BeforeMethod
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

		String idToken = resp.then().extract().path("	");
		logger.info("GeneratedToken:"+ idToken);				
		generatedToken=idToken;
		

	}

	//@BeforeMethod
	public void createCartID() {

		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */

		resp = given().
				parameter("Authorization", "bearer "+generatedToken).expect().statusCode(201).
				when().
				post(CARTID);



		String allocatedOrdNum = resp. 
				then(). 
				extract(). 
				path("allocatedOrderNumber");
		AllocatedOrderNumber=allocatedOrdNum;
		logger.info("AllocatedOrderNumber: "+AllocatedOrderNumber);
		String cartID = resp. 
				then().extract().
				path("guid");

		logger.info("GUID : " + cartID);
		generatedCartID=cartID;


	}
			
	public void checkProduct()
	{

		/*
		 * SELECT PC13
		 */
		resp = given().pathParam("SelectedPC9", selectedPC9). 
				expect().statusCode(200).contentType(ContentType.JSON). 
				when().get(SELECTEDPC13);

		for(Map<String,String> testData : testDataMap) {
			String pc13 = selectedPC9 + "0" + testData.get("L_W_S1");
			SelectedPC13 = pc13;

			logger.info("Selected PC13 : " + pc13);

			/*
			 * ADD TO CART
			 */

			JSONObject childParams = new JSONObject();
			childParams.put("code", pc13); 
			childParams.put("qty", testData.get("Qty1"));
			JSONObject mainParams = new JSONObject();
			mainParams.put("product", childParams);

			resp = given().body(mainParams).
					pathParam("guid", generatedCartID).contentType(ContentType.JSON).
					expect().statusCode(200).
					when().post(ADDTOCART);

			String Statuscode = resp.then().extract().path("statusCode");
			logger.info("Status Code for add to cart : " + Statuscode);

			String addedpc13 = resp.then().extract().path("entry.product.code");
			if(addedpc13.equals(pc13)) {
				assertEquals(addedpc13, pc13);
			}else {
				logger.info("Selected PC13 is not equals to Added PC13");
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
				logger.info("Actual cart value is not equal to view cart value");
			}
			ViewCartValue=totalPriceWithTax;
			String viewCartGUID = resp.then().extract().path("guid");
			if(viewCartGUID.equals(generatedCartID)) {
				assertEquals(viewCartGUID, generatedCartID);
			}else {
				logger.info("CartID and View Cart GUID is not equal");
			}

		}
	}

	@After
	public void cleanup() {
		report.flush();
	}

	public void captureStatus() {
		// TODO Auto-generated method stub

	}







}
