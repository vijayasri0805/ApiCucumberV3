Feature: This feature will remove products from the cart completely.

Scenario: Validate Remove cart scenario by deleting the products from the cart
	Given initialize the Remove Cart test suite 
	Then get the auth token for Remove Cart
	And create the cartID and Allocated Order number for Remove Cart
	Then create the order using Remove Cart
	And capture the status of the Remove Cart scenarios
	Then flush the pervious results for Remove Cart