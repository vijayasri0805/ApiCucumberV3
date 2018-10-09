Feature: This feature will place an order using promo on aoslse app

Scenario: Validate order placement using promotions on aoslse app
	Given initialize the Promo test suite 
	Then get the auth token for Promo
	And create the cartID and Allocated Order number for Promo
	Then create the order using Promo
	And capture the status of the scenarios
	Then flush the pervious results
	
