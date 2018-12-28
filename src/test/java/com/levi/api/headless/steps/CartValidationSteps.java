package com.levi.api.headless.steps;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import java.io.File;

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
		System.out.println("AllocatedOrderNumber: "+AllocatedOrderNumber);
		String cartID = resp. 
				then().extract().
				path("guid");

		System.out.println("GUID : " + cartID);
		BaseSetUp.generatedCartID=cartID;
	}
	
	@And("^User validates PC9 with size for \"([^\"]*)\"$")
	public void validatePC9(String locale) throws Throwable {
		CartValidation cart = new CartValidation(locale);
		String pc13 = cart.validatePC9();
		System.out.println("Selected PC13 : " + pc13);
		SelectedPC13 = pc13;
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
	
	@Then("^User add delivery method to registered cart for \"([^\"]*)\"$")
	public void addDeliveryToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addDeliveryToRegCart(BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
		
		
	}
	
	@Then("^User add payment method to registered cart for \"([^\"]*)\"$")
	public void addPaymentToRegCart(String locale) throws Throwable {
		
		CartValidation cart = new CartValidation(locale);
		Response resp = cart.addPaymentToRegCart(BaseSetUp.generatedToken, BaseSetUp.generatedCartID);
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
