Feature: This feature will check the hybris api features
	 
@HEAD-144
Scenario Outline: HEAD-144 - Make shipping address default
	Given User generates registered auth token for "<Locale>"
	Given User pulls first non default shipping address for "<Locale>"
	Given User marks one address as default for "<Locale>"
Examples:
|Locale|
|GB|

