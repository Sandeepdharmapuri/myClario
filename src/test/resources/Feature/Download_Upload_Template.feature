@download_template
Feature: Verify download &  upload templates

  Background:
    Given the user is on the landing page
    When the user clicks the Get Started button
    Then the user should be redirected to the Sign-in page
    When I enter email and password
    And I click on Sign In button
    Then I should be redirected to the dashboard

  Scenario Outline: verify Download templates
    Given the user is on the Dashboard
    And User click contacts option on left side panel
    When User click on download template button
    Then I should see a success toast message as file download

  Scenario Outline: verify upload templates
    Given the user is on the Dashboard
    And User click contacts option on left side panel
    When User clicks on upload template button
    Then I should see a success toast message as file upload

  @TC_03
  Scenario Outline: verify Duplicate contacts keep as unique
    Given the user is on the Dashboard
    And User click contacts option on left side panel
    When User click on review duplicate three dot icon

  # And click on Keep as Unique button
  # Then I should see a success toast message
  @TC_04
  Scenario Outline: verify Duplicate contacts keep as unique
    Given the user is on the Dashboard
    And User click contacts option on left side panel
    When User merge the contact
    
    
      @TC_05
  Scenario Outline: verify Duplicate contacts cancel button
    Given the user is on the Dashboard
    And User click contacts option on left side panel
	When User cancel merge contact pop-up
