package com.levi.api.headless.steps;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.levi.api.headless.BaseSetUp;
import com.levi.api.utils.PropertyReader;

import cucumber.api.java.Before;

public class Hook {
	/*
	@Before
	public void startUp()
	{
		PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
		List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();
		
		String locale = System.getProperty("locale");
		BaseSetUp.SCAN = reader.getData("scanBarCode");
		if(locale.equalsIgnoreCase("us"))
		{
			BaseSetUp.PC9 = reader.getData("usPC9");
			BaseSetUp.SIZE = reader.getData("usSize");
			BaseSetUp.QTY = reader.getData("usQty");
			BaseSetUp.BASEURL = reader.getData("baseURL").replace("{locale}", "US");
		}
		else if(locale.equalsIgnoreCase("ca"))
		{
			BaseSetUp.PC9 = reader.getData("caPC9");
			BaseSetUp.SIZE = reader.getData("caSize");
			BaseSetUp.QTY = reader.getData("caQty");
			BaseSetUp.BASEURL = reader.getData("baseURL").replace("{locale}", "CA");
		}
		else if(locale.equalsIgnoreCase("eu"))
		{
			BaseSetUp.PC9 = reader.getData("euPC9");
			BaseSetUp.SIZE = reader.getData("euSize");
			BaseSetUp.QTY = reader.getData("euQty");
			BaseSetUp.BASEURL = reader.getData("baseURL").replace("{locale}", "EU");
		}
		BaseSetUp.CARTID = BaseSetUp.BASEURL+reader.getData("createCartID");
		BaseSetUp.PRODUCTDATA = BaseSetUp.BASEURL+reader.getData("productData");
		BaseSetUp.SELECTEDPC13 = BaseSetUp.BASEURL+reader.getData("selectPC13");
		BaseSetUp.ADDTOCART = BaseSetUp.BASEURL+reader.getData("addToCart");
		BaseSetUp.VIEWCART = BaseSetUp.BASEURL+reader.getData("viewCart");
		BaseSetUp.PLACEORDER = BaseSetUp.BASEURL+reader.getData("placeOrder");
		BaseSetUp.CHECKOUT = BaseSetUp.BASEURL+reader.getData("checkOut");
		BaseSetUp.AUTHTOKEN = reader.getData("authToken");
		
	}
*/
}
