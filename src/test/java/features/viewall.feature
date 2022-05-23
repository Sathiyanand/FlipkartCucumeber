@viewall
Feature: ViewAll functionality

Scenario: validate ViewAll functionality

Given Initalize browser with chrome
And Navigate to "https://www.flipkart.com" HomePage
When User click on ViewAll button
Then User Navigates to Product Listing Page
And Close browser

