Feature: This feature will check the hybris api features
 
Scenario Outline: Validate add to cart feature
	Given User generates auth token for "<Locale>"
	Then User creates a anonymous cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to cart for "<Locale>"
	And User view the cart and validate for "<Locale>"
Examples:
|Locale|
|US|
|CA|
|GB|
@HEAD-129	
Scenario Outline: HEAD-129 - Validate registered user checkout feature
	Given User generates registered auth token for "<Locale>"
	Then User creates a registered cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to registered cart for "<Locale>"
	Then User verifies delivery method to registered cart for "<Locale>"
	Then User verifies delivery method for invalid UID for "<Locale>"
	Then User verifies delivery method for invalid GUID for "<Locale>"
	Examples:
	|Locale|
	|GB|
