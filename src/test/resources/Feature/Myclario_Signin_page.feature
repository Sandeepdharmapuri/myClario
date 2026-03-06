@SigninPage
Feature: MyClario Signin Page

  Background:
    Given the user is on the landing page
    When the user clicks the Get Started button
    Then the user should be redirected to the Sign-in page

  @TC_01
  Scenario: Successful login with valid credentials
    When I enter email and password
    And I click on Sign In button
    Then I should be redirected to the dashboard
