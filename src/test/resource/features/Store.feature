Feature: This feature will check the hybris api features

@HEAD-99
Scenario Outline: HEAD-99 - Validate Store Services
	Then User validates detail of BaseStore for "<Locale>"
Examples:
|Locale|
|US|

@HEAD-101
Scenario Outline: HEAD-101 - Validate Store Services
	Then User validates country detail of BaseStore for "<Locale>"
Examples:
|Locale|
|GB|
|US|
|CA|

@HEAD-101
Scenario Outline: HEAD-101 - Validate Store Services
	Then User validates region detail of BaseStore for "<Locale>"
Examples:
|Locale|
|US|
|CA|

