Feature: Contact Us Form and Test Cases Page Verification

  Scenario Outline: Contact Us Form
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    And User clicks on Contact Us button
    Then Verify GET IN TOUCH is visible
    When User enters "<name>", "<email>", "<subject>", and "<message>"
    And User uploads file from path "<Path>"
    And User clicks on Submit button
    And User clicks OK on the alert
    Then Verify success message Success! Your details have been submitted successfully. is visible
    When User clicks on Home button
    Then Verify that home page should be visible successfully
    And user close the window
    Examples:
      | name   | email             | subject  | message                |Path|
      | Trisha | testhome@mail.com | Feedback | This is a test message |Files/test.txt|

  Scenario: Verify Test Cases Page
    Given User launches the browser
    When User navigates to "http://automationexercise.com"
    Then Verify that home page should be visible successfully
    When User clicks on Test Cases button
    Then Verify that user is navigated to test cases page successfully
    And user close the window