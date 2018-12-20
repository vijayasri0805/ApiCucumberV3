Feature: This feature will place an order End to End Happy Path

Scenario: Validate HappyPath to place an order
	Given initialize the test suite for Happy Path
	Then get the token for HappyPath
	And create cartID and GUID for Happy Path
	When user places an order using Happy Path
	Then capture the status of the Happy scenario
	And clean up the pervious results of Happy Path