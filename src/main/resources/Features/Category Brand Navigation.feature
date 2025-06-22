@AutomationTag2
Feature: View Products by Category and Brand

  Scenario: View products by category
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify the Categories should be visible on the left sidebar
    When User clicks on the Women category
    And User selects a sub-category under Women
    Then Category page should be displayed with title WOMEN - DRESS PRODUCTS
    When User clicks a sub-category under Men
    Then User should be navigated to MEN - TSHIRTS PRODUCTS category page
    And user close the window

  Scenario: View and cart brand products
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    When User clicks on Products button
    Then Validate the Brand list should be visible on the left sidebar
    When User selects brand Polo
    Then User should be navigated to the brand page showing products for Polo
    When User clicks on another brand H&M
    Then User should be navigated to the brand page showing products for H&M
    And user close the window