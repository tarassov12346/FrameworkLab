@Registration
Feature: Registration page
  In order to test Registration Page of test-shop
  As a unregistered user
  I want to check the functionality of Registration page

  Background: I'm on the home page of the site
    Given Open the main page

  Scenario: User try to register with valid credentials
    When user opens registration page
    And user enters name in name input
    And user enters  email in email input
    And user enters password in password input
    And user repeats password in repeated password input
    And user clicks submit registration button
    Then successful registration message "Теперь вы можете войти используя свой email и пароль!" appears