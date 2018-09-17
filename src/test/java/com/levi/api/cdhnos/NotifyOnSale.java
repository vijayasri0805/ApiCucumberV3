package com.levi.api.cdhnos;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class NotifyOnSale {
	
	
	
	
	@Test
	public void testConsumerID() {
		
		Response resp = given().pathParam("ConsumerID", "00419145-33c1-45c2-9212-99c83e027031").
					
				when().
				get("https://qa-api.levi-site.com/consumer/v1/{ConsumerID}/notifications"). 
				then(). 
				contentType(ContentType.JSON). 
				extract(). 
				path("notifications[*].notification_object_id");
		
		System.out.println("Notification On Sale =" + resp);
				
		
	}
}
