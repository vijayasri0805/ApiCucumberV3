package com.levi.api.headless;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.levi.api.utils.PropertyReader;

public class BaseSetUp {
	
	public String BASEURL;
	public String PC9;
	public String SIZE;
	public String QTY;

	public String SCAN;
	public String ANONCARTID;
	public String ANONCARTTOGUEST;
	public String REGCARTID;
	public String AUTHTOKEN;
	public String PRODUCTDATA;
	public String LISTOFCONSENTSDATA;
	public String SPECIFICCONSENTDATA;
	public String DELETECONSENTDATA;
	public String CONSENTTEMPLATEID;
	public String CONSENTTEMPLATEVERSION;
	public String ADDCONSENT;
	public String SWATCHDATA;
	public String SELECTEDPC13;
	public String ADDTOANONCART;
	public String ADDTOREGCART;
	public String VIEWCART;
	public String PLACEORDER;
	public String CHECKOUT;
	public String STOCKAVAILABILITY;
	public String PROMO;
	public String CREATEREGADDRESS;
	public String ADDREGDELIVERY;
	public String GETREGDELIVERY; 
	public String ADDPAYMENTREGCART;
	public String DELIVERYMETHOD;
	public String SELECTDELIVERYMETHOD;
	public String RESERVEINVENTORY;
	public String CHECKOUTREGORDER;
	public String STOREIDASSOID;
	public String PAYMENT;	
	public String UID;
	public String PWD;
	public String CATEGORYSORT;
	public String CATEGORYDETAILS;
	public String CATEGORYPAGE;
	public String GETSAVEDADDR;
	public String MARKADDRDEFAULT;
	public String GETSAVEDPAYMENT;
	public String MARKPAYMENTDEFAULT;
	public String CREATEUSER;
	public String BASESTOREDETAILS;
	public String COUNTRYDETAILS;
	public String REGIONDETAILS;
	public String UPDATECART;
	
	public String firstName;
	public String lastName;
	public String addrLine1;
	public String addrLine2;
	public String town;
	public String isocode;
	public String postalCode;
	public String phone;
	public String deliveryMode;
	public String accountHolderName;
	public String visaCardNumber;
	public String masterCardNumber;
	public String amexCardNumber;
	public String maestroCardNumber;
	public String discoverCardNumber;
	public String dinersCardNumber;
	public String expiryMonth;
	public String expiryYear;
	public String adyenPaymentMethod;
	public String baseStoreID;
	public String estimatedDeliveryDate;
	
	
	
	public  static String generatedToken;
	public static String generatedCartID;
	
	public BaseSetUp(String locale)
	{
		PropertyReader reader = new PropertyReader("src/test/resource/testdata/test-data.properties");
		List<Map<String, String>> testDataMap = new LinkedList<Map<String,String>>();
		
		SCAN = reader.getData("scanBarCode");
		if(locale.equalsIgnoreCase("us"))
		{
			UID = reader.getData("usUID");
			PWD = reader.getData("usPWD");
			PC9 = reader.getData("usPC9");
			SIZE = reader.getData("usSize");
			QTY = reader.getData("usQty");
			town = reader.getData("ustown");
			isocode = reader.getData("usisocode");
			postalCode = reader.getData("uspostalCode");
			phone = reader.getData("usphone");
			deliveryMode = reader.getData("usDeliveryMode");
			baseStoreID = reader.getData("usBaseStoreID");
			BASEURL = reader.getData("baseURL").replace("{locale}", "US");
			
		}
		else if(locale.equalsIgnoreCase("ca"))
		{
			UID = reader.getData("caUID");
			PWD = reader.getData("caPWD");
			PC9 = reader.getData("caPC9");
			SIZE = reader.getData("caSize");
			QTY = reader.getData("caQty");
			town = reader.getData("caTown");
			isocode = reader.getData("caIsocode");
			postalCode = reader.getData("caPostalCode");
			phone = reader.getData("caPhone");
			deliveryMode = reader.getData("caDeliveryMode");
			baseStoreID = reader.getData("caBaseStoreID");
			BASEURL = reader.getData("baseURL").replace("{locale}", "CA");
		}
		else if(locale.equalsIgnoreCase("gb"))
		{
			UID = reader.getData("gbUID");
			PWD = reader.getData("gbPWD");
			PC9 = reader.getData("gbPC9");
			SIZE = reader.getData("gbSize");
			QTY = reader.getData("gbQty");
			town = reader.getData("gbTown");
			isocode = reader.getData("gbIsocode");
			postalCode = reader.getData("gbPostalCode");
			phone = reader.getData("gbPhone");
			deliveryMode = reader.getData("gbDeliveryMode");
			baseStoreID = reader.getData("gbBaseStoreID");
			BASEURL = reader.getData("baseURL").replace("{locale}", "GB");
		}
		else if(locale.equalsIgnoreCase("pl"))
		{
			UID = reader.getData("plUID");
			PWD = reader.getData("plPWD");
			PC9 = reader.getData("plPC9");
			SIZE = reader.getData("plSize");
			QTY = reader.getData("plQty");
			town = reader.getData("plTown");
			isocode = reader.getData("plIsocode");
			postalCode = reader.getData("plPostalCode");
			phone = reader.getData("plPhone");
			deliveryMode = reader.getData("plDeliveryMode");
			baseStoreID = reader.getData("plBaseStoreID");
			BASEURL = reader.getData("baseURL").replace("{locale}", "PL");
		}
		
		AUTHTOKEN = reader.getData("authToken");
		ANONCARTID = BASEURL+reader.getData("createAnonCartID");
		ANONCARTTOGUEST = BASEURL+reader.getData("convertAnonCartToGuest");
		REGCARTID = BASEURL+reader.getData("createRegCartID");
		PRODUCTDATA = BASEURL+reader.getData("productData");
		LISTOFCONSENTSDATA = BASEURL+reader.getData("listOfConsents");
		SPECIFICCONSENTDATA = BASEURL+reader.getData("getSpecificConsents");
		DELETECONSENTDATA = BASEURL+reader.getData("deleteSpecificConsents");
		CONSENTTEMPLATEID = reader.getData("consentTemplateId");
		CONSENTTEMPLATEVERSION = reader.getData("consentTemplateVersion");
		ADDCONSENT = BASEURL+reader.getData("addConsents");
		SELECTEDPC13 = BASEURL+reader.getData("selectPC13");
		ADDTOANONCART = BASEURL+reader.getData("addToAnonCart");
		ADDTOREGCART = BASEURL+reader.getData("addToRegCart");
		VIEWCART = BASEURL+reader.getData("viewCart");
		UPDATECART = BASEURL+reader.getData("updateCart");
		PLACEORDER = BASEURL+reader.getData("placeOrder");
		CHECKOUT = BASEURL+reader.getData("checkOut");		
		CREATEREGADDRESS = BASEURL+reader.getData("createRegAddress");
		GETREGDELIVERY = BASEURL+reader.getData("getRegDeliveryMethod");
		ADDREGDELIVERY = BASEURL+reader.getData("addRegDeliveryMethod");
		ADDPAYMENTREGCART = BASEURL+reader.getData("addRegPaymentMethod");
		CHECKOUTREGORDER = BASEURL+reader.getData("checkoutRegUser");
		SWATCHDATA = BASEURL+reader.getData("swatchData");
		CATEGORYSORT = BASEURL+reader.getData("categorySort");
		CATEGORYPAGE = BASEURL+reader.getData("categoryPage");
		GETSAVEDADDR = BASEURL+reader.getData("getSavedAddress");
		MARKADDRDEFAULT = BASEURL+reader.getData("markSavedAddrDefault");
		GETSAVEDPAYMENT = BASEURL+reader.getData("getSavedPayment");
		MARKPAYMENTDEFAULT = BASEURL+reader.getData("markSavedPaymentDefault");
		CREATEUSER = BASEURL+reader.getData("createUser");
		CATEGORYDETAILS = BASEURL+reader.getData("categoryDetails");
		BASESTOREDETAILS = BASEURL+reader.getData("baseStoreDetails");
		COUNTRYDETAILS = BASEURL+reader.getData("countryDetails");
		REGIONDETAILS = BASEURL+reader.getData("regionDetails");
		
		firstName = reader.getData("firstName");
		estimatedDeliveryDate = reader.getData("estimatedDeliveryDate");
		lastName = reader.getData("lastName");
		addrLine1 = reader.getData("line1");
		addrLine2 = reader.getData("line2");
		accountHolderName = reader.getData("accountHolderName");
		visaCardNumber = reader.getData("visaCardNumber");
		masterCardNumber = reader.getData("masterCardNumber");
		amexCardNumber = reader.getData("amexCardNumber");
		maestroCardNumber = reader.getData("maestroCardNumber");
		discoverCardNumber = reader.getData("discoverCardNumber");
		dinersCardNumber = reader.getData("dinersCardNumber");
		expiryMonth = reader.getData("expiryMonth");
		expiryYear = reader.getData("expiryYear");
		adyenPaymentMethod = reader.getData("adyenPaymentMethod");
	}

}
