Feature: This feature will check the hybris api features

Scenario Outline: Validate add to cart feature
	Given User adds product to cart for "<Locale>"
	Examples:
	|Locale|
	|US|
	|CA|