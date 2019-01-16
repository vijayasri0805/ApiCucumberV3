Feature: This feature will check the hybris api features

@HEAD-99
Scenario Outline: HEAD-99 - Validate Store Services
	Then User validates detail of BaseStore for "<Locale>"
Examples:
|Locale|
|GB|
