Feature: Checkout Address Verification, Invoice Download, and Scroll Functionality

  Scenario Outline: Verify address details in checkout page
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    When User enters valid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Logged in as Name should be visible
    When User clicks on Products button
    When User enters "<SearchKeyword>" in the search input and clicks the search button
    When User adds one searched products to cart
    And User clicks on Cart button
    Then Verify Searched products should be visible in the cart
    When User clicks Proceed To Checkout
    Then Validate the Delivery address should match the registration address
    And Validate the billing address should match the registration address
    And user close the window

    Examples:
      | SearchKeyword | Email              | Password  |
      | dress         | komal@testmail.com | Komal@123 |

  Scenario Outline: Download Invoice after purchase order
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    When User enters valid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Logged in as Name should be visible
    When User clicks on Products button
    When User enters "<SearchKeyword>" in the search input and clicks the search button
    When User adds one searched products to cart
    And User clicks on Cart button
    Then Verify Searched products should be visible in the cart
    When User clicks Proceed To Checkout
    Then Verify Address Details and Review Order
    When User enters "<comment>" and clicks on Place Order
    And User enters payment details with "<Name>" on Card, "<Card Number>", "<CVC>", "<Expiration>"
    And User clicks on Pay and Confirm Order
    Then Validate the success message Congratulations! Your order has been confirmed! should be visible
    When User clicks on Download Invoice button
    Then Invoice should be downloaded successfully
    When User clicks on Continue button
    And user close the window
    Examples:
      | SearchKeyword | Email              | Password  | comment             | Name     | Card Number      | CVC | Expiration |
      | dress         | komal@testmail.com | Komal@123 | Please deliver fast | John Doe | 1234567812345678 | 123 | 12/27      |

  Scenario: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User scrolls down to footer
    Then User should see text SUBSCRIPTION
    When User clicks on the arrow button at bottom right to scroll up
    Then Validate Full-Fledged practice website for Automation Engineers text should be visible

  Scenario: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User scrolls down to footer
    Then User should see text SUBSCRIPTION
    When User manually scrolls up to the top of the page
    Then Validate Full-Fledged practice website for Automation Engineers text should be visible