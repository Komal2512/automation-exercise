@AutomationTag5
Feature: Verify Subscription Functionality on Home and Cart Pages

  Scenario Outline: Verify subscription functionality on Home Page
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User scrolls down to footer
    Then User should see text SUBSCRIPTION
    When User enters email "<Email>" in the subscription input and clicks the arrow button
    Then A success message You have been successfully subscribed! should be visible
    And user close the window
    Examples:
      |  Email|
    |testhome@mail.com|

  Scenario Outline: Verify subscription functionality on Cart Page
    Given User launches the browser
    When User navigates to "https://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Cart button
    And User scrolls down to footer
    Then User should see text SUBSCRIPTION
    When User enters email "<Email>" in the subscription input and clicks the arrow button
    Then A success message You have been successfully subscribed! should be visible
    And user close the window
    Examples:
      |  Email|
      |testhome@mail.com|