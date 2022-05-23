@searchproduct
Feature: SearchProduct functionality

Scenario: Validate SearchProduct functionality

Given Initalize browser with chrome
And Navigate to "https://www.flipkart.com" HomePage
When User enter "realme C21Y Mobiles"
And User click on search button
Then User Navigates to Product Listing Page with products as searched
And Close browser