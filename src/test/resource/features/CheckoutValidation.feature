Feature: This feature will check the hybris api features

@HEAD-241 @testTag
Scenario Outline: HEAD-241, HEAD-234 - Validate registered user checkout feature
	Given User generates registered auth token for "<Locale>"
	Then User creates a registered cart for "<Locale>"
	And User validates PC9 with size for "<Locale>"
	Then User adds product to registered cart for "<Locale>"
	And User add address to registered cart for "<Locale>"
	Then User add delivery method to registered cart for "<Locale>"
	And User add "<Payment>" method to registered cart for "<Locale>"
	Then User proceed to registered checkout for "<Locale>"
	Examples:
	|Payment||Locale|
	|Visa||GB|
	|Master||GB|
	|Amex||GB|
	|Maestro||GB|
	|Discover||PL|
	|Diners||PL|
