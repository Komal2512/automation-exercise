@AutomationTag7
Feature: Place Order with Registration and Login Scenarios

  Scenario Outline: Place Order - Register during checkout
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Products button
    And user adds the first product to the cart
    And User clicks on View Cart button
    When User clicks Proceed To Checkout
    And User clicks on Register or Login button
    When User enters name "<NamePrefix>" and email address "<EmailPrefix>"
    And User click on Signup button
    When User enters account details: "<title>", "<password>", "<day>", "<month>", "<year>"
    And Selects checkbox Sign up for our newsletter!
    And Selects checkbox Receive special offers from our partners!
    And Fills in address details: "<FirstName>", "<LastName>", "<Company>", "<Address1>", "<Address2>", "<Country>", "<State>", "<City>", "<Zipcode>", "<MobileNumber>"
    And Clicks on Create Account button
    Then Verify ACCOUNT CREATED! message should be visible
    When User clicks on Continue button
    Then Logged in as Name should be visible
    And User clicks on Cart button
    When User clicks Proceed To Checkout
    Then Verify Address Details and Review Order
    When User enters "<comment>" and clicks on Place Order
    And User enters payment details with "<Name>" on Card, "<Card Number>", "<CVC>", "<Expiration>"
    And User clicks on Pay and Confirm Order
    Then Validate the success message Congratulations! Your order has been confirmed! should be visible
    When User clicks on Delete Account button
    Then Verify ACCOUNT DELETED! message should be visible
    And User clicks on Continue button
    And user close the window

    Examples:
      | NamePrefix  | EmailPrefix | title | password   | day | month    | year | FirstName | LastName | Company | Address1 | Address2 | Country | State | City      | Zipcode | MobileNumber | comment                              | Name     | Card Number      | CVC | Expiration |
      | Trisha Goel | trisha      | Mrs   | Trisha@123 | 5   | December | 2000 | Trisha    | Goel     | EFG Ltd | Street 2 | Apt 102  | India   | Delhi | New Delhi | 110001  | 8899999999   | Please deliver between 9 AM and 5 PM | John Doe | 4111111111111111 | 723 | 12/30      |


  Scenario Outline: Place Order - Register before checkout
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    When User enters name "<NamePrefix>" and email address "<EmailPrefix>"
    And User click on Signup button
    When User enters account details: "<title>", "<password>", "<day>", "<month>", "<year>"
    And Selects checkbox Sign up for our newsletter!
    And Selects checkbox Receive special offers from our partners!
    And Fills in address details: "<FirstName>", "<LastName>", "<Company>", "<Address1>", "<Address2>", "<Country>", "<State>", "<City>", "<Zipcode>", "<MobileNumber>"
    And Clicks on Create Account button
    Then Verify ACCOUNT CREATED! message should be visible
    When User clicks on Continue button
    Then Logged in as Name should be visible
    When User clicks on Products button
    And user adds the first product to the cart
    And User clicks on Cart button
    When User clicks Proceed To Checkout
    Then Verify Address Details and Review Order
    When User enters "<comment>" and clicks on Place Order
    And User enters payment details with "<Name>" on Card, "<Card Number>", "<CVC>", "<Expiration>"
    And User clicks on Pay and Confirm Order
    Then Validate the success message Congratulations! Your order has been confirmed! should be visible
    When User clicks on Delete Account button
    Then Verify ACCOUNT DELETED! message should be visible
    And User clicks on Continue button
    And user close the window

    Examples:
      | NamePrefix   | EmailPrefix | title | password    | day | month | year | FirstName | LastName | Company | Address1 | Address2 | Country | State | City      | Zipcode | MobileNumber |comment                              | Name     | Card Number      | CVC | Expiration |
      | Trishna Goel | trishna     | Mrs   | Trishna@123 | 22  | December   | 2000 | Trishna   | Goel     | GHJ Ltd | Street 2 | Apt 101  | India   | Delhi | New Delhi | 110001  | 8899995999   |Please deliver between 9 AM and 5 PM | John Doe | 4111111111111111 | 723 | 12/30      |

  Scenario Outline: Place Order - Login before checkout
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    When User enters valid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Logged in as Name should be visible
    When User clicks on Products button
    And user adds the first product to the cart
    And User clicks on Cart button
    When User clicks Proceed To Checkout
    Then Verify Address Details and Review Order
    When User enters "<comment>" and clicks on Place Order
    And User enters payment details with "<Name>" on Card, "<Card Number>", "<CVC>", "<Expiration>"
    And User clicks on Pay and Confirm Order
    Then Validate the success message Congratulations! Your order has been confirmed! should be visible
    When User clicks on Delete Account button
    Then Verify ACCOUNT DELETED! message should be visible
    And User clicks on Continue button
    And user close the window

    Examples:
      | Email                | Password    |comment                              | Name     | Card Number      | CVC | Expiration |
      | test1use@example.com | Test@123 |Leave the package at the front door | Test User | 4012888888881881 | 456 | 10/27     |