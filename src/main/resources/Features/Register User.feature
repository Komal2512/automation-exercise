@AutomationTag9
Feature: Automation Exercise - Register User Validation

  Scenario Outline: User registers on the website
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    Then Verify New User Signup! section should be visible
    When User enters name "<NamePrefix>" and email address "<EmailPrefix>"
    And User click on Signup button
    Then Verify ENTER ACCOUNT INFORMATION section should be visible
    When User enters account details: "<title>", "<password>", "<day>", "<month>", "<year>"
    And Selects checkbox Sign up for our newsletter!
    And Selects checkbox Receive special offers from our partners!
    And Fills in address details: "<FirstName>", "<LastName>", "<Company>", "<Address1>", "<Address2>", "<Country>", "<State>", "<City>", "<Zipcode>", "<MobileNumber>"
    And Clicks on Create Account button
    Then Verify ACCOUNT CREATED! message should be visible
    When User clicks on Continue button
    Then Logged in as Name should be visible
    When User clicks on Delete Account button
    Then Verify ACCOUNT DELETED! message should be visible
    And User clicks on Continue button
    And user close the window

    Examples:
      | NamePrefix | EmailPrefix | title | password  | day | month | year | FirstName | LastName | Company | Address1 | Address2 | Country | State | City      | Zipcode | MobileNumber |
      | Komal Goel | komal       | Mrs   | Komal@123 | 5   | May   | 2000 | Komal     | Goel     | ABC Ltd | Street 1 | Apt 202  | India   | Delhi | New Delhi | 110001  | 9999999999   |

  Scenario: User tries to register with an already registered email
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    Then Verify New User Signup! section should be visible
    When User enter name and already registered email address
    And User click on Signup button
    Then Verify error Email Address already exist! is visible
    And user close the window
