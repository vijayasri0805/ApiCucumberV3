package com.levi.api.headless;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
import com.jayway.restassured.response.ValidatableResponse;

import net.minidev.json.JSONObject;

public class CartValidation {


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
	
	public CartValidation(String locale)
	{
		baseSetUp = new BaseSetUp(locale);
	}
	

	public void setup(String locale) throws Exception {

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/target/AOSLSEHappyScenario.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extentReport-config.xml"));
		report=new ExtentReports();
		report.setSystemInfo("Environment", "Headless");
		report.attachReporter(htmlReporter);
	}

	public Response createAnonCartID(String generatedToken) {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		Response resp = given().
				parameter("Authorization", "bearer "+generatedToken).expect().statusCode(201).
				when().
				post(baseSetUp.ANONCARTID);

		return resp;

	}
	
	public Response createRegCartID(String generatedToken) {
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
		 */
		System.out.println("REGCARTID:"+baseSetUp.REGCARTID.replace("{UID}", baseSetUp.UID));
		System.out.println("Token: "+generatedToken);
		Response resp = given().
				pathParam("UID", baseSetUp.UID).contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).//expect().statusCode(201).
				when().
				post(baseSetUp.REGCARTID);
		return resp;

	}
	
	public String validatePC9()
	{
		/*
		 * PRODUCTDATA + Select PC13
		 */
		System.out.println("PRODUCTDATA:"+baseSetUp.PRODUCTDATA);
		resp = given().pathParam("PC9", baseSetUp.PC9).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().get(baseSetUp.PRODUCTDATA);
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
		System.out.println("SELECTEDPC13: "+baseSetUp.SELECTEDPC13);
		resp = given().pathParam("SelectedPC9", selectedpc9). 
				expect().statusCode(200).contentType(ContentType.JSON). 
				when().get(baseSetUp.SELECTEDPC13);
		String pc13 = selectedPC9 + "0" + baseSetUp.SIZE;
		SelectedPC13 = pc13;

		return pc13;
		
	}

	public Response addToAnonCart(String SelectedPC13, String generatedCartID)
	{

		
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * ADD TO CART
		 */

		JSONObject childBody = new JSONObject();
		childBody.put("code", SelectedPC13); 
		childBody.put("qty", baseSetUp.QTY);
		JSONObject mainBody = new JSONObject();
		mainBody.put("product", childBody);

		resp = given().body(mainBody).
				pathParam("guid", generatedCartID).contentType(ContentType.JSON).
				expect().statusCode(200).
				when().post(baseSetUp.ADDTOANONCART);

		return resp;

		
	}
	
	public Response addToRegCart(String SelectedPC13, String generatedCartID, String generatedToken)
	{
		//testInfo = report.createTest("Test Scenario : Product");

		/*
		 * ADD TO CART
		 */

		JSONObject childBody = new JSONObject();
		childBody.put("code", SelectedPC13); 
		childBody.put("qty", baseSetUp.QTY);
		JSONObject mainBody = new JSONObject();
		mainBody.put("product", childBody);

		resp = given().body(mainBody).
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).
				when().post(baseSetUp.ADDTOREGCART);

		return resp;

		
	}
	
	public Response addAddressToRegCart(String generatedCartID)
	{
		/*
		 * ADD ADDRESS TO CART
		 */
		JSONObject mainBody = new JSONObject();
		mainBody.put("lastName", baseSetUp.firstName);
		mainBody.put("firstName", baseSetUp.lastName);
		mainBody.put("line1", baseSetUp.addrLine1);
		mainBody.put("line2", baseSetUp.addrLine2);
		mainBody.put("town", baseSetUp.town);
		
		JSONObject childBody = new JSONObject();		
		childBody.put("isocode", baseSetUp.isocode);
		mainBody.put("country", childBody);
		
		mainBody.put("postalCode", baseSetUp.postalCode);
		mainBody.put("phone", baseSetUp.phone);
		
		resp = given().body(mainBody).
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+BaseSetUp.generatedToken).expect().statusCode(201).
				when().post(baseSetUp.CREATEREGADDRESS);				
		
		return resp;
	}
	public Response updateAddressToRegCart( String generatedCartID){
		JSONObject updateBody = new JSONObject();
		updateBody.put("estimatedDeliveryDate", baseSetUp.estimatedDeliveryDate);
		resp = given().body(updateBody).
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+BaseSetUp.generatedToken).expect().
				when().post(baseSetUp.UPDATECART);
		System.out.println(resp.body().toString());
		return resp;
	}
	public Response addDeliveryToRegCart(String generatedToken, String generatedCartID) {
		/*
		 * Add Delivery Method
		 */
		Response resp = given().
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				pathParam("dMode", baseSetUp.deliveryMode).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).
				when().
				put(baseSetUp.ADDREGDELIVERY);

		return resp;

	}
	
	public Response verifyDeliveryToRegCart(String generatedToken, String generatedCartID) {
		/*
		 * Verify Delivery Method
		 */
		Response resp = given().
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(200).
				when().
				get(baseSetUp.GETREGDELIVERY);

		return resp;

	}
	public Response verifyDeliveryToRegCartForInvalidUID(String generatedToken, String generatedCartID) {
		/*
		 * Verify Delivery Method-- Invalid UID
		 */
		Response resp = given().
				pathParam("UID", "somail@gmail.com").
				pathParam("guid", generatedCartID).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(400).
				when().
				get(baseSetUp.GETREGDELIVERY);

		return resp;

	}
	public Response verifyDeliveryToRegCartForInvalidGUID(String generatedToken, String generatedCartID) {
		/*
		 * Add Delivery Method--Invalid GUID
		 */
		Response resp = given().
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(400).
				when().
				get(baseSetUp.GETREGDELIVERY);

		return resp;

	}
	
	public Response addPaymentToRegCart(String paymentMethod, String generatedToken, String generatedCartID)
	{
		/*
		 * ADD ADDRESS TO CART
		 */
		JSONObject paymentDetails = new JSONObject();
		JSONObject cardType = new JSONObject();
		paymentDetails.put("accountHolderName", baseSetUp.accountHolderName);
		switch(paymentMethod.toLowerCase())
		{
		case "visa":
			paymentDetails.put("cardNumber", baseSetUp.visaCardNumber);
			cardType.put("code", "visa");
			paymentDetails.put("cardType", cardType);
			break;
		case "master":
			paymentDetails.put("cardNumber", baseSetUp.masterCardNumber);
			cardType.put("code", "master");
			paymentDetails.put("cardType", cardType);
			break;
		case "amex":
			paymentDetails.put("cardNumber", baseSetUp.amexCardNumber);
			cardType.put("code", "amex");
			paymentDetails.put("cardType", cardType);
			break;
		case "maestro":
			paymentDetails.put("cardNumber", baseSetUp.maestroCardNumber);
			cardType.put("code", "maestro");
			paymentDetails.put("cardType", cardType);
			break;
		case "discover":
			paymentDetails.put("cardNumber", baseSetUp.discoverCardNumber);
			cardType.put("code", "discover");
			paymentDetails.put("cardType", cardType);
			break;
		case "diners":
			paymentDetails.put("cardNumber", baseSetUp.dinersCardNumber);
			cardType.put("code", "diners");
			paymentDetails.put("cardType", cardType);
			break;
		default:
			System.out.println("Wrong Card type selected: "+paymentMethod);
		}		
		
		paymentDetails.put("expiryMonth", baseSetUp.expiryMonth);
		paymentDetails.put("expiryYear", baseSetUp.expiryYear);
		
		
		JSONObject billingAddress = new JSONObject();
		billingAddress.put("firstName", baseSetUp.firstName);
		billingAddress.put("lastName", baseSetUp.lastName);		
		billingAddress.put("line1", baseSetUp.addrLine1);
		billingAddress.put("line2", baseSetUp.addrLine2);
		billingAddress.put("postalCode", baseSetUp.postalCode);
		billingAddress.put("town", baseSetUp.town);
		
		JSONObject country = new JSONObject();		
		country.put("isocode", baseSetUp.isocode);
		billingAddress.put("country", country);
		paymentDetails.put("billingAddress", billingAddress);
		
		paymentDetails.put("saved", true);
		paymentDetails.put("adyenPaymentMethod", baseSetUp.adyenPaymentMethod);
		paymentDetails.put("defaultPayment", false);
		
		//System.out.println(paymentDetails.toJSONString());
		
		//mainBody.put("phone", baseSetUp.phone);
		
		resp = given().body(paymentDetails).
				pathParam("UID", baseSetUp.UID).
				pathParam("guid", generatedCartID).
				contentType(ContentType.JSON).
				header("Authorization", "bearer "+generatedToken).expect().statusCode(201).
				when().post(baseSetUp.ADDPAYMENTREGCART);				
		
		return resp;
	}
	
	public Response viewAndValidateCart(String generatedCartID)
	{/*
		 * VIEW CART
		 */

		resp = given().
				pathParameter("guid", generatedCartID).
				when().get(baseSetUp.VIEWCART);
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
