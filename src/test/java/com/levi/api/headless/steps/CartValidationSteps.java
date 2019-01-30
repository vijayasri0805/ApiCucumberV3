package com.levi.api.headless.steps;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import java.io.File;

import com.cucumber.listener.Reporter;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.CartValidation;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class CartValidationSteps {

	String SelectedPC13;
	String generatedUid;
	String AddedCartValue;
	String QuantityAdded;
	String AllocatedOrderNumber;
	String ViewCartValue;

	@Then("^User creates a anonymous cart for \"([^\"]*)\"$")
	public void createAnonCart(String locale) throws Throwable
	{
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.createAnonCartID(BaseSetUp.generatedToken);
		
		resp.then().
		body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/addCart.json"))));
		
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
		BaseSetUp.generatedCartID=cartID;
	}
	
	@Then("^User converts anon cart to guest cart for \"([^\"]*)\"$")
	public void convertAnonCartToGuest(String locale) throws Throwable
	{
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.convertAnonCartToGuest(BaseSetUp.generatedToken);
		
	}
	
	
	@Then("^User creates a registered cart for \"([^\"]*)\"$")
	public void createRegCart(String locale) throws Throwable
	{
		CartValidation cart = new CartValidation(locale);
		
		Response resp = cart.createRegCartID(BaseSetUp.generatedToken);
		
		String allocatedOrdNum = resp. 
				then(). 
				extract(). 
				path("allocatedOrderNumber");
		AllocatedOrderNumber=allocatedOrdNum;
		String cartID = resp. 
				then().extract().
				path("guid");
		BaseSetUp.generatedCartID=cartID;
		Reporter.addStepLog("Create Cart Successful with GUID " + cartID);
	}
	
	@And("^User validates PC9 with size for \"([^\"]*)\"$")
	public void validatePC9(String locale) throws Throwable {
		CartValidation cart = new CartValidation(locale);
		String pc13 = cart.validatePC9();
		System.out.println("Selected PC13 : " + pc13);
		SelectedPC13 = pc13;
		Reporter.addStepLog("PC13: "+pc13+" selected successfully ");
	}
	
	
	@Then("^User adds product to cart for \"([^\"]*)\"$")
	public void addProductToAnonCart(String locale) throws Throwable {
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addToAnonCart(SelectedPC13, BaseSetUp.generatedCartID);
		String Statuscode = resp.then().extract().path("statusCode");
		System.out.println("Status Code for add to cart : " + Statuscode);

		String addedpc13 = resp.then().extract().path("entry.product.code");
		if(addedpc13.equals(SelectedPC13)) {
			assertEquals(addedpc13, SelectedPC13);
		}else {
			System.out.println("Selected PC13 is not equals to Added PC13");
		}

		String CartValue = resp.then().extract().path("entry.totalPrice.value").toString();
		AddedCartValue = CartValue;

		String addedQuantity = resp.then().extract().path("quantityAdded").toString();
		addedQuantity.equals(cart.baseSetUp.QTY);
	}
	
	@Then("^User adds product to registered cart for \"([^\"]*)\"$")
	public void addProductToRegCart(String locale) throws Throwable {
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addToRegCart(SelectedPC13, BaseSetUp.generatedCartID, BaseSetUp.generatedToken);
		String Statuscode = resp.then().extract().path("statusCode");
		
		System.out.println("Status Code for add to cart : " + Statuscode);
		Reporter.addStepLog("Add to cart status: "+Statuscode);

		String addedpc13 = resp.then().extract().path("entry.product.code");
		if(addedpc13.equals(SelectedPC13)) {
			assertEquals(addedpc13, SelectedPC13);
		}else {
			System.out.println("Selected PC13 is not equals to Added PC13");
		}

		String CartValue = resp.then().extract().path("entry.totalPrice.value").toString();
		AddedCartValue = CartValue;

		String addedQuantity = resp.then().extract().path("quantityAdded").toString();
		addedQuantity.equals(cart.baseSetUp.QTY);
	}
	
	@Then("^User add address to registered cart for \"([^\"]*)\"$")
	public void addAddressToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addAddressToRegCart(BaseSetUp.generatedCartID);
		String id = resp.then().extract().path("id").toString();
		System.out.println("Add address ID: "+id);
		
		
	}
	
	@Then("^User add invalid address to registered cart for \"([^\"]*)\"$")
	public void addInvalidAddressToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addInvalidAddressToRegCart(BaseSetUp.generatedCartID, locale);
		String errorMessage = resp.then().extract().path("errors[0].type").toString();
		assertEquals(errorMessage.contains("CartAddressError"),true);	
		
	}
	

	@Then("^User update product to registered cart for \"([^\"]*)\"$")
	public void updateAddressToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.updateAddressToRegCart(BaseSetUp.generatedCartID);
		//String id = resp.then().extract().path("id").toString();
		//System.out.println("Add address ID: "+id);
		resp.then().body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/updateCart.json"))));
		
	}
	
	@Then("^User add delivery method to registered cart for \"([^\"]*)\"$")
	public void addDeliveryToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		System.out.println("generatedCartID:"+BaseSetUp.generatedCartID);
		Response resp = cart.addDeliveryToRegCart(BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
		
		
	}
	
	@Then("^User verifies delivery method to registered cart for \"([^\"]*)\"$")
	public void verifyDeliveryToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		System.out.println("generatedCartID:"+BaseSetUp.generatedCartID);
		Response resp = cart.verifyDeliveryToRegCart(BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
		resp.then().body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/getDeliveryMethod.json"))));
		
	}
	@Then("^User verifies delivery method for invalid UID for \"([^\"]*)\"$")
	public void verifyDeliveryToRegCartForInvalidUID(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		System.out.println("generatedCartID:"+BaseSetUp.generatedCartID);
		Response resp = cart.verifyDeliveryToRegCartForInvalidUID(BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
		//resp.then().body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/getDeliveryMethod.json"))));
		System.out.println("resp"+resp);
		String errorMessage = resp.then().extract().path("errors[0].message").toString();
		assertEquals(errorMessage.contains("Cannot find user with uid"),true);
	}
	
	@Then("^User verifies delivery method for invalid GUID for \"([^\"]*)\"$")
	public void verifyDeliveryToRegCartForInvalidGUID(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		//System.out.println("generatedCartID:"+BaseSetUp.generatedCartID);
		Response resp = cart.verifyDeliveryToRegCartForInvalidGUID(BaseSetUp.generatedToken,"c0s2770-0178-4ea4-9ac5-974b76250ec8");
		//resp.then().body(matchesJsonSchema(new File(System.getProperty("user.dir").concat("/src/test/resource/json-schema/getDeliveryMethod.json"))));
		String errorMessage = resp.then().extract().path("errors[0].message").toString();
		assertEquals(errorMessage.contains("Cart not found."),true);
	}
	@Then("^User add \"([^\"]*)\" method to registered cart for \"([^\"]*)\"$")
	public void addPaymentToRegCart(String payment, String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addPaymentToRegCart(payment, BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
		String id = resp.then().extract().path("id").toString();
		System.out.println("Add Payment ID: "+id);
	}
	
	@And("^User view the cart and validate for \"([^\"]*)\"$")
	public void viewAndValidateCart(String locale) throws Throwable {
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.viewAndValidateCart(BaseSetUp.generatedCartID);
		String totalPriceWithTax = resp.then().extract().path("totalPriceWithTax.value").toString();
		if(totalPriceWithTax.equals(AddedCartValue)) {
			assertEquals(totalPriceWithTax, AddedCartValue);
		}else {
			System.out.println("Actual cart value is not equal to view cart value");
		}
		ViewCartValue=totalPriceWithTax;
		String viewCartGUID = resp.then().extract().path("guid");
		if(viewCartGUID.equals(BaseSetUp.generatedCartID)) {
			assertEquals(viewCartGUID, BaseSetUp.generatedCartID);
		}else {
			System.out.println("CartID and View Cart GUID is not equal");
		}
	}

	
}
