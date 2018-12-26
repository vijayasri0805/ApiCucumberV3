Feature: This feature will check the hybris api features
	 
Scenario Outline: Validate add to cart feature
	Given User generates auth token for "<Locale>"
	Then User creates a cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to cart for "<Locale>"
	And User view the cart and validate for "<Locale>"
Examples:
|Locale|
|US|
|CA|