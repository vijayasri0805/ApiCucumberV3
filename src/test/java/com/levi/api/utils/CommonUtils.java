package com.levi.api.utils;

import static com.jayway.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.levi.api.headless.BaseSetUp;

public class CommonUtils {
	
	
	public static String GenerateRandomEmail()
	{
		String Randomresult = RandomStringUtils.random(64, false, true);
		Randomresult = RandomStringUtils.random(3,0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
		String mydate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		String mailID = Randomresult+mydate+"@levi.com";
		return mailID;
	}

}

