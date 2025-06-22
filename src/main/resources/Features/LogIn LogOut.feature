Feature: User Login and Logout Functionality

  Scenario Outline: Login with incorrect credentials
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    Then User Login to your account should be visible
    When User enters invalid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Error message "<ErrorMsg>" should be visible
    And user close the window
    Examples:
      | Email          | Password  | ErrorMsg                             |
      | wrong@mail.com | wrongpass | Your email or password is incorrect! |

  Scenario Outline: Logout after successful login
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Signup or Login button
    Then User Login to your account should be visible
    When User enters valid email "<Email>" and password "<Password>"
    And user clicks on Login button
    Then Logged in as Name should be visible
    When User clicks on Logout button
    Then User should be redirected to login page
    And user close the window
    Examples:
      | Email              | Password |
      | komal@testmail.com | Komal@123 |