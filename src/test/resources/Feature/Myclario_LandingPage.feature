# Author: Manoj Kumar Behera
# Business Need: Positive and Negative Testcases
# Scenarios Covered : 18
@LandingPage
Feature: MyClario_Landing Page

  @TC_01
  Scenario: Clicking Get Started navigates to Sign-in page (positive)
    Given the user is on the landing page
    When the user clicks the Get Started button
    Then the user should be redirected to the Sign-in page
