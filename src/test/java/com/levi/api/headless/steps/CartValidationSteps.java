package com.levi.api.headless.steps;


import static org.testng.Assert.assertEquals;

import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;
import com.levi.api.headless.CartValidation;
import com.levi.api.utils.CommonUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CartValidationSteps {

	public CartValidation product = new CartValidation();

	String generatedCartID;
	String generatedToken;
	String SelectedPC13;
	String generatedUid;
	String AddedCartValue;
	String QuantityAdded;
	String AllocatedOrderNumber;
	String ViewCartValue;
	
	@Given("^User generates auth token$")
	public void generateAuthToken() throws Throwable
	{
		Response resp = new CommonUtils().authToken();
		System.out.println(resp.body());
		generatedToken = resp.then().extract().path("access_token");

	}

	@Then("^User creates a cart$")
	public void createCart() throws Throwable
	{
		Response resp = product.createCartID(generatedToken);
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
	
	@And("^User validates PC9 with size$")
	public void validatePC9() throws Throwable {
		String pc13 = product.validatePC9();
		System.out.println("Selected PC13 : " + pc13);
		SelectedPC13 = pc13;
	}
	
	
	@Then("^User adds product to cart$")
	public void add_product_to_cart() throws Throwable {
		Response resp = product.addToCart(SelectedPC13, generatedCartID);
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
		addedQuantity.equals(BaseSetUp.QTY);
	}
	
	@And("^User view the cart and validate$")
	public void viewAndValidateCart() throws Throwable {
		Response resp = product.viewAndValidateCart(generatedCartID);
		String totalPriceWithTax = resp.then().extract().path("totalPriceWithTax.value").toString();
		if(totalPriceWithTax.equals(AddedCartValue)) {
			assertEquals(totalPriceWithTax, AddedCartValue);
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

	
}
