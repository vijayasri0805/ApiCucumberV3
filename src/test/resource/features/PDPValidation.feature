Feature: This feature will check the hybris api features

@PDP
Scenario Outline: Validate product details
	Given User validates product detail for "<Locale>"
	And User checks the swatch data for "<Locale>"
Examples:
|Locale|
|GB|
