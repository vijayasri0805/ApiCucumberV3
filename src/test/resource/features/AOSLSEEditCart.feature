Feature: This feature will place an order using promo on aoslse app

	

Scenario: Validate EditCart to remove all the products from the cart
	Given get the auth token for EditCart
	And create the cartID and Allocated Order number for EditCart
	Then remove products from the cart