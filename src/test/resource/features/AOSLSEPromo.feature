Feature: This feature will place an order using promo on aoslse app

Scenario: Validate order placement using promotions on aoslse app
	Given get the auth token 
	And create the cartID and Allocated Order number
	Then place an order using AOSLSE promo.
	
