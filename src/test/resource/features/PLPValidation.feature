Feature: This feature will check the hybris api features
	 
@HEAD-373
Scenario Outline: HEAD-373 - Validate PLP sort
	Given User validates "<Category>" detail with sort "<SortType>" for "<Locale>"
Examples:
|Category||SortType||Locale|
|levi_clothing_women_jeans||price-asc||GB|

@HEAD-285
Scenario Outline: HEAD-285 - Validate category breadcrumbs
	Given User validates breadcrumbs for "<Category>" service for "<Locale>"
Examples:
|Category||Locale|
|levi_clothing_women_jeans||GB|
