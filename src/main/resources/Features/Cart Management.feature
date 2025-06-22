Feature: Shopping Cart Management

  Scenario: Adding multiple products to cart
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    And user adds the first product to the cart
    And user chooses to Continue Shopping
    And user adds the second product to the cart
    And User clicks on View Cart button
    Then Verify the Both products should be present in the cart
    And Validate the each product's price, quantity and total should be correctly displayed
    And user close the window

  Scenario Outline: Verify product quantity in cart
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    When User clicks on View Product of the first product
    Then User should be navigated to product detail page
    When user sets the quantity to "<quantity>"
    And user adds the product to the cart
    And User clicks on View Cart button
    Then user cart should reflect the product with quantity "<quantity>"
    And user close the window

    Examples:
      | quantity |
      | 4        |

  Scenario: Removing a product from the cart
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    And user adds the first product to the cart
    And User clicks on View Cart button
    When user removes the product from the cart
    Then Validate the product should no longer be present in the cart
    And user close the window