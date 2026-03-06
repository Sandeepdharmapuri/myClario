Feature: Delete Contact
Background:
Given the user is on the landing page
When the user clicks the Get Started button
Then the user should be redirected to the Sign-in page
 When I enter email and password
  And I click on Sign In button
  Then I should be redirected to the dashboard
  
  Scenario Outline: Delete contact (positive)
  Given the user is on the Dashboard
  When User click contacts option on left side panel
  And User click on three dots and Edit button of contact
  And Click on Delete button
  And Click on final delete