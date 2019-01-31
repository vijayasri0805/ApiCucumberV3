Feature: This feature will check the hybris api features
	 
@HEAD-144
Scenario Outline: HEAD-144 - Make shipping address default
	Given User generates registered auth token for "<Locale>"
	Given User pulls first non default shipping address for "<Locale>"
	Given User marks one address as default for "<Locale>"
Examples:
|Locale|
|GB|

@HEAD-125
Scenario Outline: HEAD-125 - Make shipping address default
	Given User generates registered auth token of savedaddress user for "<Locale>"
	Given User pulls first non default payment method for "<Locale>"
	Given User marks one payment as default for "<Locale>"
Examples:
|Locale|
|GB|

@HEAD-100
Scenario Outline: HEAD-100 - Fetch List of Consents
	Given User generates registered auth token for "<Locale>"
	Given User adds consents for "<Locale>"
	Given User pulls list of consents used by Customer "<Locale>"
	Then User pulls specific consent "<consentTemplateId>" for customer in "<Locale>"
	Given User Delete consent "<consentTemplateId>" for customer in "<Locale>"
Examples:
|Locale|consentTemplateId|
|GB|TERMS_OF_USE|

