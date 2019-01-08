Feature: This feature will check the hybris api features
	 
Scenario Outline: Validate product details
	Given User validates "<Category>" detail with sort "<SortType>" for "<Locale>"
Examples:
|Category||SortType||Locale|
|levi_clothing_women_jeans||price-asc||GB|
