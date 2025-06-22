Feature: Product Details and Search Functionality

  Scenario: Verify all products and product detail page
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    Then User should be navigated to ALL PRODUCTS page successfully
    And  products list should be visible
    When User clicks on View Product of the first product
    Then User should be navigated to product detail page
    And User product details like name, category, price, availability, condition, and brand should be visible
    And user close the window

  Scenario Outline: Verify product search functionality
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    Then User should be navigated to ALL PRODUCTS page successfully
    When User enters "<SearchKeyword>" in the search input and clicks the search button
    Then Verify SEARCHED PRODUCTS should be visible
    And Validate the all products related to "<SearchKeyword>" should be visible
    And user close the window

    Examples:
      | SearchKeyword |
      | dress         |
      | top           |
      | tshirt        |

  Scenario Outline: Search product, add to cart and verify cart persists after login
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    Then User should be navigated to ALL PRODUCTS page successfully
    When User enters "<SearchKeyword>" in the search input and clicks the search button
    Then Verify SEARCHED PRODUCTS should be visible
    And Validate the all products related to "<SearchKeyword>" should be visible
    When User adds one searched products to cart
    And User clicks on Cart button
    Then Verify Searched products should be visible in the cart
    And User clicks on Signup or Login button
    When User enters valid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Logged in as Name should be visible
    When User navigates back to Cart page
    Then Validate previously added searched products should still be visible in the cart
    And user close the window

    Examples:
      | SearchKeyword | Email              | Password  |
      | dress         | komal@testmail.com | Komal@123 |

  Scenario: Add a Recommended Product to Cart
    Given User launches the browser
    And User navigates to "http://automationexercise.com"
    And User scrolls down to footer
    Then Verify RECOMMENDED ITEMS section should be visible
    When User clicks on Add To Cart button of a first recommended product
    And User clicks on View Cart button
    Then Validate the recommended product should be displayed in the cart page
    And user close the window

  Scenario Outline: Submit a product review successfully
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    Then User should be navigated to ALL PRODUCTS page successfully
    When User clicks on View Product button of any product
    Then Validate Write Your Review section should be visible
    When User enters "<name>", "<email>", and "<review>"
    And User clicks on Review Submit button
    Then Validate the success message Thank you for your review. should be visible
    And user close the window
    Examples:
      | name  | email              | review                           |
      | Komal | komal@testmail.com | Great quality and fast delivery! |
