package com.levi.api.aoslse;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.utils.PropertyReader;
import com.levi.api.utils.TestDataUtils;

import net.minidev.json.JSONObject;

public class Product {
	
	
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
	
	public String CHECKOUT;
	
	public String STOCKAVAILABILITY;
	
	public String PROMO;
	
	public String CREATEADDRESS;
	
	public String DELIVERYMETHOD;
	
	public String SELECTDELIVERYMETHOD;
	
	public String RESERVEINVENTORY;
	
	public String STOREIDASSOID;
	
	public String PAYMENT;
	
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
	Integer standardShipping;
	Float upsExpress;
	String standardShippingName;
	String expressShippingName;
	String reservStatus;
	List<Array> reservQty;
	
	PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
	List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();
	
	@BeforeSuite
	public void setup() throws Exception {
		
		SCAN = reader.getData("scanBarCode");
		CARTID = reader.getData("createCartID");
		PRODUCTDATA = reader.getData("productData");
		SWATCHDATA = reader.getData("swatchData");
		SELECTEDPC13 = reader.getData("selectPC13");
		ADDTOCART = reader.getData("addToCart");
		VIEWCART = reader.getData("viewCart");
		PROMO = reader.getData("promo");
		CHECKOUT = reader.getData("checkOut");
		STOCKAVAILABILITY = reader.getData("stockAvailability");
		CREATEADDRESS = reader.getData("createAddress");
		DELIVERYMETHOD = reader.getData("deliveryMethods");
		SELECTDELIVERYMETHOD = reader.getData("selectDeliveryMethods");
		RESERVEINVENTORY = reader.getData("reserveInventory");
		STOREIDASSOID = reader.getData("storeIDAssoID");
		PAYMENT = reader.getData("paymentDetails");
		PLACEORDER = reader.getData("placeOrder");
		AUTHTOKEN = reader.getData("authToken");
		
		testDataMap = TestDataUtils.readFromFileAndConvertToMap(new File("src/test/resource/testdata/AOSLSE.csv"));
		
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
				JSONObject requestParams = new JSONObject();
				requestParams.put("username", "testaoswebclient"); 
				requestParams.put("password", "Test@0swebClient");
				
				resp = given().body(requestParams.toJSONString()). 
						expect().statusCode(200).contentType(ContentType.JSON).
						when().
						post(AUTHTOKEN);
				
				
				String idToken = resp. 
						then().extract().path("id_token");
				System.out.println(idToken);
				
				
				generatedToken=idToken;
		
	}
	

	public void createCartID() {
		
		/*
		 * GUID AND ALLOCATED ORDER NUMBER
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
		
		
	}
	@Test			
	public void checkProduct()
	{
		testInfo = report.createTest("Test Scenario : Product");
		
		/*
		 * PRODUCTDATA
		*/
		resp = given().pathParam("PC9", "045112369").
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
				pathParam("SelectedPC9", "045112369"). 
				expect().statusCode(200).contentType(ContentType.JSON).
				when().get(SWATCHDATA);
		
		String swatchAvailability = resp. 
				then().
				extract().
				path("swatchAvailabilities[5].variantsAvailability").toString();	
		
		System.out.println("Swatch Availablity:"+swatchAvailability);
		
		String swatchSelectedProductCode = resp. 
				then(). 
				extract(). 
				path("swatchAvailabilities[5].code");
		
		System.out.println("Swatch Selected PC9 : " + swatchSelectedProductCode);
		
	}

	public void happy() {
			
			testInfo = report.createTest("Test Scenario : Promo");
			for(Map<String,String> testData : testDataMap) {		
			
				
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
				 * CHECKOUT CUSTOMER
				 */
				
				resp = given().body("cartId="+generatedCartID+"&userId="+testData.get("EmailID")).contentType(ContentType.JSON). 
						auth().oauth2(generatedToken).expect().statusCode(200). 
						when().post(CHECKOUT);
				
				String uid = resp.then().extract().path("uid");
				generatedUid=uid;
				System.out.println(uid);
				
				
				/*
				 * CREATE ADDRESS
				 */
				
				resp = given().body("lastName="+testData.get("LastName")
								+"&firstName="+testData.get("FirstName")
								+"&line1="+testData.get("Line1")
								+"&line2="+testData.get("Line2")
								+"&town="+testData.get("town")
								+"&country.isocode=FR&postalCode="+testData.get("PostalCode")
								+"&phone="+testData.get("Phone")). 
						pathParameter("UID", generatedUid).
						pathParameter("guid", generatedCartID).
						contentType(ContentType.JSON).
						auth().oauth2(generatedToken).expect().statusCode(200). 
						when().post(CREATEADDRESS);
				
				/*
				 * STOCK AVAILABILITY
				 */
				
				resp = given().
						pathParameter("UID", generatedUid).
						pathParameter("guid", generatedCartID).
						contentType(ContentType.JSON).
						auth().oauth2(generatedToken).
						expect().statusCode(200).
						when().get(STOCKAVAILABILITY);
				
				List<String> itemID = resp. 
						then().extract().path("skus.itemId");
				System.out.println("StockAvailability PC13 is : " + itemID);
				
				
				List<Integer> quantity = resp.then().extract().path("skus.quantity");
				System.out.println(quantity);
				
				List<String> status = resp.then().extract().path("skus.status");
				
				if (quantity.get(0)>=6) {
					assertEquals(status.get(0), "inStock");
				}else if (quantity.get(0)<=6) {
					assertEquals(status.get(0), "lowStock");
				}else if (quantity.get(0)<=0) {
					assertEquals(status.get(0), "outOfStock");
				}
					
				
				/*
				 * AVAILABLE DELIVERY MODES
				 */
				
				resp = given().pathParameter("UID", generatedUid).
						pathParameter("guid", generatedCartID).auth().oauth2(generatedToken). 
						expect().statusCode(200).when().get(DELIVERYMETHOD);
				
				Integer freeShippingValue = resp.then().extract().path("deliveryModes[0].deliveryCost.value");
				standardShipping = freeShippingValue;
				
				Float upsValue = resp.then().extract().path("deliveryModes[1].deliveryCost.value");
				upsExpress = upsValue;
				
				String freeShippingName = resp.then().extract().path("deliveryModes[0].code");
				standardShippingName = freeShippingName;
				
				String upsShippingName = resp.then().extract().path("deliveryModes[1].code");
				expressShippingName = upsShippingName;
				
				
				/*
				 * SELECT DELIVERY METHODS
				 */
				
				resp = given().body("deliveryModeId=colisprive").
						pathParameter("UID", generatedUid).
						pathParameter("guid", generatedCartID).contentType(ContentType.JSON). 
						auth().oauth2(generatedToken).expect(). 
						statusCode(200).when(). 
						put(SELECTDELIVERYMETHOD);
				
				
				/*
				 * RESERVE INVENTORY
				 */
				
				resp = given().pathParameter("UID", generatedUid).
						pathParameter("guid", generatedCartID).auth().oauth2(generatedToken). 
						expect().statusCode(200).when().
						get(RESERVEINVENTORY);
				
				String reservationStatus = resp.then().extract().path("reservationStatus");
				reservStatus = reservationStatus;
				System.out.println(reservationStatus);
				
				List<Array> reservationQuantity = resp.then().extract().path("itemList.orderedQuantity");
				reservQty = reservationQuantity;
				
				/*
				 * STORE ID AND ASSOCIATE ID
				 */
				
				resp = given().body("storeId=store1&associateId=associate1").pathParameter("UID", generatedUid). 
						pathParameter("guid", generatedCartID).contentType(ContentType.JSON). 
						auth().oauth2(generatedToken).expect(). 
						statusCode(200).when().put(STOREIDASSOID);
				
				/*
				 *  PAYMENT DETAILS
				 */
				
				resp = given().body("pspReference=32566565633232359&Status=Approved&orderTxnRef="+AllocatedOrderNumber+"&pspAuthCode=12343333399&cardBin=679999&cardHolderName=AOSLSE TestAPI&cardNumber=9990&cardType=visa&cardExpiryYear=2020&cardExpiryMonth=8"). 
						pathParameter("UID", generatedUid). 
						pathParameter("guid", generatedCartID).
						contentType(ContentType.JSON).auth().
						oauth2(generatedToken).expect(). 
						statusCode(200).when().post(PAYMENT);
				
				/*
				 * PLACE ORDER
				 */
				
				resp = given().body("cartId="+generatedCartID). 
						pathParameter("UID", generatedUid). 
						contentType(ContentType.JSON).auth().
						oauth2(generatedToken).expect(). 
						statusCode(200).when().post(PLACEORDER);
				
				String orderNumber = resp.then().extract().path("allocatedOrderNumber");
				if (orderNumber==AllocatedOrderNumber) {
					assertEquals(AllocatedOrderNumber, orderNumber);
				}else {
					System.out.println("Actual Order Number is not equal to Allocated Order Number");
				}
				System.out.println("Order Number : " + orderNumber);
				System.out.println("Completed Happy Path Scenario");
				
				testInfo.log(Status.PASS, "Selected PC9 from Response is : " + selectedPC9);
				testInfo.log(Status.PASS, "Selected PC13 from Response is : " + SelectedPC13);
				testInfo.log(Status.PASS, "Acutal Price of the selected PC13 is : " + totalPriceWithTax);
				testInfo.log(Status.PASS, "Quantity available for the selected PC13 is : " + quantity);
				testInfo.log(Status.PASS, "Stock Level of the selected PC13 is : " + status);
				testInfo.log(Status.PASS, "Reservation Status is : " + reservStatus);
				testInfo.log(Status.PASS, "Reserved Quantity of the PC13 is : " + reservQty);
				testInfo.log(Status.PASS, "Order Number is : " + orderNumber);
					
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
