@addtocart
Feature: AddToCart Count Update 

Scenario: Validate Cart Count increase in product listing page

Given Initalize browser with chrome
And Navigate to "https://www.flipkart.com" HomePage
And User enter "realme C21Y Mobiles"
And User click on search button
And User selects the "realme C21Y (Cross Black, 64 GB)"
And User moves to product Description page
And User add item to cart
And User moves to product listing page
When User refresh product listing page 
Then Cart Count in product listing page gets increased
And Close browser