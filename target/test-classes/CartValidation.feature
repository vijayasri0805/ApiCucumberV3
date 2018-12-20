Feature: These test scenario's are used to test the Cart API's of Hybris

Scenario: Validate add an item to cart for guest user and  Validate the cart
	Given initialize the test suite
	Then get the auth token for EditCart
	And create the cartID and Allocated Order number for EditCart
	Then remove products from the cart
	And capture the status of the EditCart Scenario
	Then flush the pervious results for the EditCart scenario