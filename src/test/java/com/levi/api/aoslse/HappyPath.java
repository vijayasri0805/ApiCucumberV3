package com.levi.api.aoslse;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import net.minidev.json.JSONObject;

public class HappyPath {
	String generatedCardID;
	String generatedToken;
	String generatedUid;

	
	public String getSelectedPC9() {

		StringBuilder pc9Data=new StringBuilder();
		Response resp = given().
				parameter("code", "3114438924696").
				parameter("fields", "FULL").
				when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/products/scan");
		
		String pc9 = resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("baseOptions[0].selected.code");


		System.out.println("Scan PC9 : " + pc9);


		pc9Data.append(pc9).append("-");
				String nameOfProduct = String.valueOf(resp.
						then().
						contentType(ContentType.JSON).
						extract().
						path("altText"));
				
				System.out.println("Product Name : "+ nameOfProduct);
				
				
				
				
				String  pc13 = resp.
						then().
						contentType(ContentType.JSON).
						extract().
						path("variantOptions[0].code");
				
				System.out.println("Selected PC13 : " + pc13);
				pc9Data.append(pc13);
				
				return pc9Data.toString();
				
				
				

	}


	@BeforeClass
	public void createCartIDAndGuid() {
		

		Response resp = given().
				
				when().
				contentType(ContentType.JSON).
				post("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/users/anonymous/carts");
		String allocatedOrdNum = resp. 
				then(). 
				extract(). 
				path("allocatedOrderNumber");
		
		System.out.println("Allocated Order Number : " + allocatedOrdNum);
		
		String cartID = resp. 
				then().extract().
				path("guid");
				
		System.out.println("GUID : " + cartID);
		generatedCardID=cartID;
		//return cartID;
		
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "testaoswebclient"); 
		requestParams.put("password", "Test@0swebClient");
		
		Response respAuth = given().body(requestParams.toJSONString()).
				contentType(ContentType.JSON).
				expect(). 
				statusCode(200). 
				when().
				post("https://reg-200.levi-site.com/api/aos/authorizationserver/oauth/token");
		
		System.out.println(respAuth.asString());
		
		String idToken = respAuth. 
				then().extract().path("id_token");
		System.out.println("Auth Token : " + idToken);
		
		generatedToken=idToken;
		
		
		//String cartID = generatedCardID;
		System.out.println("Get card id:"+generatedCardID);
		String authToken=generatedToken;
		System.out.println(generatedCardID);
		String gUID="cartId="+generatedCardID+"&userId=test123@yupmail.com";
		System.out.println(gUID);
		
		Response respUid = given().body("cartId="+cartID+"&userId=test123@yupmail.com").
				contentType(ContentType.JSON).
				auth().oauth2(authToken).expect().statusCode(200).when().post("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/aoslse/users/carts");
			System.out.println(respUid.asString());
			
			String uid = respUid.then().extract().path("uid");
			System.out.println("UID : " + uid);
			generatedUid=uid;
			//return uid;
				
				}
	
	@Test
	public void productData() {
		String pc9Data=getSelectedPC9();
		String scannedPC9=pc9Data.substring(0, pc9Data.indexOf("-"));
		System.out.println("-->"+scannedPC9);
		
		Response resp = given().
				pathParameter("Scan PC9", scannedPC9).
				expect().
				statusCode(200).
				when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/products/{Scan PC9}");
		
		String itemData = resp. 
				then().
				extract().
				path("price.formattedValue");
		
		System.out.println("Price : " + itemData);
		
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
				
		
	}
	
	@Test
	public void swatchData() {
		
		String pc9Data=getSelectedPC9();
		String swatchPC9=pc9Data.substring(0, pc9Data.indexOf("-"));
		
		//String swatchPC9 = getSelectedPC9();
		System.out.println("--->"+swatchPC9);
		
		Response resp = given(). 
				pathParameter("Swatch PC9", swatchPC9). 
				expect(). 
				statusCode(200). 
				when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/products/{Swatch PC9}/swatches");
//		JsonPath js=resp.jsonPath().getJsonObject("swatchAvailabilities[5].variantsAvailability");
//		js.getList("swatchAvailabilities[5].variantsAvailability");
		
		String swatchAvailability = resp. 
				then().
				extract().
				path("swatchAvailabilities[5].variantsAvailability").toString();	
		//JsonPath js=JsonPath.from(swatchAvailability);
		
		System.out.println("Sizes Available : " + swatchAvailability);
		
//		for(var i = 0; i < js.getJlength; i++) {
//			   var obj = swatchAvailability[i];
//
//			   console.log(obj.id);
//			}
		//[{size=10A, available=true}, {size=12A, available=true}, {size=8A, available=true}, {size=6A, available=true}, {size=14A, available=true}, {size=4A, available=true}, {size=5A, available=true}, {size=16A, available=true}, {size=2A, available=true}, {size=3A, available=true}]
		String swatchSelectedProductCode = resp. 
				then(). 
				extract(). 
				path("swatchAvailabilities[5].code");
		
		System.out.println("Swatch Selected PC9 : " + swatchSelectedProductCode);
		
		
		
		
	}
	
	
	public String addToCart() {
		
		String cartID=generatedCardID;
		
		String pc9Data=getSelectedPC9();
		String pc13=pc9Data.substring(pc9Data.indexOf("-")+1, pc9Data.length());
		
		System.out.println("-->"+pc13);
		
		String testcode="code="+pc13+"&qty=1";
		System.out.println(testcode);
		Response resp = given().body("code="+pc13+"&qty=1").
				pathParameter("cartGUID", cartID).
				contentType(ContentType.JSON).
				expect(). 
				statusCode(200). 
				when().
				post("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/users/anonymous/carts/{cartGUID}/entries");

		
		//System.out.println(resp.asString());
		
		String addedItemPrice = resp. 
				then(). 
				extract(). 
				path("entry.quantity").toString();
		
		System.out.println("Added Quantity : " + addedItemPrice);
		
		Float totalPriceOfTheItems = resp. 
				then(). 
				extract(). 
				path("entry.totalPrice.value");
		
		System.out.println("Check the Total Price of the Items which are added to the Cart : " + totalPriceOfTheItems);
		return cartID;		
		}
	
	
	public void viewCart() {
	String cartID =	generatedCardID;
	System.out.println(cartID);
		Response resp = given().
				pathParameter("cartGUID", cartID).
				when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/users/anonymous/carts/{cartGUID}");
		
		//System.out.println(resp.asString());
		
		Float totalItems = resp. 
				then().extract().path("totalPriceWithTax.value");
		System.out.println("Total Price with Tax : " + totalItems);
		
		String currencyISO = resp.then().extract().path("totalPriceWithTax.currencyIso");
		System.out.println("Currency Type : " + currencyISO);
		
	}
	
	
	
	
	
	@Test
	public void createAddress() {
		
		addToCart() ;
		viewCart();
		String uid = generatedUid;
		String authToken=generatedToken;
		String cartID = generatedCardID;
		
		System.out.println("All values.."+cartID+"-->"+uid+"-->"+generatedToken);
		
		String shippingAddress = "lastName=LeviQA&firstName=Demo&line1=10DowningStreet&line2=&town=London&country.isocode=GB&postalCode=SW1A 2AA&phone=0752345679";
		Response resp = given().body(shippingAddress). 
				contentType(ContentType.JSON).
				auth().oauth2(authToken).expect().statusCode(200).when().post("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/aoslse/users/"+uid+"/carts/"+cartID+"/addresses/delivery");
		
		System.out.println(resp);
	}

	@Test
	public void stockAvailablity() {
		
		String uid = generatedUid;
		String authToken=generatedToken;
		String cartID = generatedCardID;
		
		Response resp = given().when().contentType(ContentType.JSON).auth().oauth2(authToken).expect().statusCode(200).when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/aoslse/users/"+uid+"/carts/"+cartID+"/stockavailability");
		
		//System.out.println(resp);
	}
	
	@Test
	public void availableDeliveryMethods() {
		
		String uid = generatedUid;
		String authToken=generatedToken;
		String cartID = generatedCardID;
		
		Response resp = given().when().contentType(ContentType.JSON).auth().oauth2(authToken).expect().statusCode(200).when().
				get("https://reg-200.levi-site.com/api/aos/rest/v2/leviGBSite/aoslse/users/"+uid+"/carts/"+cartID+"/deliverymodes");
		
		System.out.println(resp);
		
	}
}
