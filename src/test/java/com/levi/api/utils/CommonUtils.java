package com.levi.api.utils;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;

public class CommonUtils {
	
	public Response authToken() {		
		/*
		 * AUTH TOKEN		
		 */
		Response resp;
		Map<String,String> params = new HashMap<String,String>();
		params.put("client_id", "headless_rest_client");
		params.put("client_secret", "Levis1234");
		params.put("grant_type", "client_credentials");

		resp = given().parameters(params).
				expect().statusCode(200).contentType(ContentType.JSON).
				when().
				post(BaseSetUp.AUTHTOKEN);
		return resp;
		

	}


}

