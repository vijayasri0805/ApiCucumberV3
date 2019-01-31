Feature: This feature will check the hybris api features
 
@AddCart
Scenario Outline: Validate add to cart feature
	Given User generates anon auth token for "<Locale>"
	Then User creates a anonymous cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to cart for "<Locale>"
	And User view the cart and validate for "<Locale>"
Examples:
|Locale|
|US|
|CA|
|GB|
@HEAD-129, @HEAD-554
Scenario Outline: HEAD-129, HEAD-554 - Validate registered user checkout feature
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
@HEAD-261	
Scenario Outline: HEAD-261 - Validate update cart feature
	Given User generates registered auth token for "<Locale>"
	Then User creates a registered cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User update product to registered cart for "<Locale>"
	Examples:
	|Locale|
	|GB|
@HEAD-655
Scenario Outline: HEAD-655 - Validate update cart feature for Annonymous User
	Given User generates anon auth token for "<Locale>"
	Then User creates a anonymous cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to cart for "<Locale>"
	Examples:
	|Locale|
	|GB|
@HEAD-561
Scenario Outline: HEAD-561 - Validate registered user checkout feature
	Given User generates registered auth token for "<Locale>"
	Then User creates a registered cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to registered cart for "<Locale>"
	And User add invalid address to registered cart for "<Locale>"
Examples:
	|Locale|
	|GB|
	
@HEAD-656
Scenario Outline: HEAD-656 - Convert anon cart to Guest cart
	Given User generates anon auth token for "<Locale>"
	Then User creates a anonymous cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to cart for "<Locale>"
	Then User converts anon cart to guest cart for "<Locale>"
	Examples:
	|Locale|
	|GB|