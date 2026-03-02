@Update_contact
Feature: Update Contact
Background:
Given the user is on the landing page
When the user clicks the Get Started button
Then the user should be redirected to the Sign-in page
 When I enter email and password
 And I click on Sign In button
 Then I should be redirected to the dashboard
  
  @TC_03
  Scenario Outline: Update contact information (positive)
  Given the user is on the Dashboard
  When User click contacts option on left side panel
  And User click on three dots and Edit button of contact
  And Update Last Name
 
 # And Click on Update contact button
 #Examples:
 #| newLastName|
 #|Nelton   |