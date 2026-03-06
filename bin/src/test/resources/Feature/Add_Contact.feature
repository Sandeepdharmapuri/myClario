@Add_contact
Feature: Click on Add Contact option
Background:
Given the user is on the landing page
When the user clicks the Get Started button
Then the user should be redirected to the Sign-in page
 When I enter email and password
  And I click on Sign In button
  Then I should be redirected to the dashboard

@TC_02
Scenario Outline: Create a contact with minimum required information (positive)
Given the user is on the Dashboard
When User click contacts option on left side panel
And User click on Add_contact button
And I enter user details
#And I select Country
#| Country |
#|India|   
#And I select State
#|State|
#|Telangana|
#And I select City
#|City|
#|Hyderabad|
#And I click Create Contact button
#Then I should see a success toast message
#And I should see contact "Sandy kumar" in the list
#Examples:
# firstName | lastName | email              | phone Number |country|
  #  |Promot    |   Kumar   |  Promot225522@gmail |9160090123|India|
   ##|Suresh    |   Kumar   |  Suresh225522@gmail |9160090125|India|
