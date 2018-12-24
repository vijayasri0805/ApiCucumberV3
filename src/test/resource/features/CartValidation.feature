Feature: This feature will check the hybris api features

Background: User prepares environment
	Given User generates auth token
	 
Scenario: Validate add to cart feature
	Then User creates a cart
	And User validates PC9 with size
	Then User adds product to cart
	And User view the cart and validate
	
