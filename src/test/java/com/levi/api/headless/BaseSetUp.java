package com.levi.api.headless;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.levi.api.utils.PropertyReader;

public class BaseSetUp {
	
	public String BASEURL;
	public   String PC9;
	public   String SIZE;
	public   String QTY;

	public   String SCAN;
	public   String CARTID;
	public   String AUTHTOKEN;
	public   String PRODUCTDATA;
	public   String SWATCHDATA;
	public   String SELECTEDPC13;
	public   String ADDTOCART;
	public   String VIEWCART;
	public   String PLACEORDER;
	public   String CHECKOUT;
	public   String STOCKAVAILABILITY;
	public   String PROMO;
	public   String CREATEADDRESS;
	public   String DELIVERYMETHOD;
	public   String SELECTDELIVERYMETHOD;
	public   String RESERVEINVENTORY;
	public   String STOREIDASSOID;
	public   String PAYMENT;	
	public  static String generatedToken;
	
	public BaseSetUp(String locale)
	{
		PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
		List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();
		
		SCAN = reader.getData("scanBarCode");
		if(locale.equalsIgnoreCase("us"))
		{
			PC9 = reader.getData("usPC9");
			SIZE = reader.getData("usSize");
			QTY = reader.getData("usQty");
			BASEURL = reader.getData("baseURL").replace("{locale}", "US");
		}
		else if(locale.equalsIgnoreCase("ca"))
		{
			PC9 = reader.getData("caPC9");
			SIZE = reader.getData("caSize");
			QTY = reader.getData("caQty");
			BASEURL = reader.getData("baseURL").replace("{locale}", "CA");
		}
		else if(locale.equalsIgnoreCase("eu"))
		{
			PC9 = reader.getData("euPC9");
			SIZE = reader.getData("euSize");
			QTY = reader.getData("euQty");
			BASEURL = reader.getData("baseURL").replace("{locale}", "EU");
		}
		CARTID = BASEURL+reader.getData("createCartID");
		PRODUCTDATA = BASEURL+reader.getData("productData");
		SELECTEDPC13 = BASEURL+reader.getData("selectPC13");
		ADDTOCART = BASEURL+reader.getData("addToCart");
		VIEWCART = BASEURL+reader.getData("viewCart");
		PLACEORDER = BASEURL+reader.getData("placeOrder");
		CHECKOUT = BASEURL+reader.getData("checkOut");
		AUTHTOKEN = reader.getData("authToken");
		
	}

}
