@getsellerrating
Feature: Get SellerRating  

Scenario: Get SellerRating from Product Description Page PopUp

Given Initalize browser with chrome
And Navigate to "https://www.flipkart.com" HomePage
And User enter "realme C21Y Mobiles"
And User click on search button
And User selects the "realme C21Y (Cross Black, 64 GB)"
And User moves to product Description page
When User clicks on seller name
Then User gets SellerRating from Popup
And Close browser