@deletecart
Feature: DeleteCart Count Update 

Scenario: Validate Cart Count decreased in product listing page when user remove product from cart

Given Initalize browser with chrome
And Navigate to "https://www.flipkart.com" HomePage
And User enter "realme C21Y Mobiles"
And User click on search button
And User selects the "realme C21Y (Cross Black, 64 GB)"
And User moves to product Description page
And User add item to cart
And Close Current browser window
And User moves to product listing page
And User refresh product listing page
And User gets Intial Cart count
And User selects the "realme C21Y (Cross Black, 32 GB)"
And User moves to product Description page
And User add item to cart
And User moves to product listing page
And User refresh product listing page 
And User gets final Cart count
And User moves to My Cart page
When User removes "realme C21Y (Cross Black, 32 GB)" from Cart
And User moves to product listing page
And User refresh product listing page
Then Cart Count Product listing page gets decresed
And Close browser