@Login
Feature: Login page
  In order to test Login Page of test-shop
  As a Registered user
  I want to check the functionality of Login page

  Background: I'm on the home page of the site
    Given Open the main page
    And I have already registered

  Scenario: User can login with valid credentials
    When user opens login page
    And user enters registered email in email input
    And user enters valid password in password input
    And user clicks submit login button
    Then header menu contains button with logged in user name
