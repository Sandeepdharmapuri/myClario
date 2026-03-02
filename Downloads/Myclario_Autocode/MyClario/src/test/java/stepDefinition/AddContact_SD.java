package stepDefinition;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.assertions.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import pageFactory.Contacts;
import preRequisites.BaseTest;

public class AddContact_SD {
	
	private WebDriver driver;
	private Contacts contactsPage;
	
	public AddContact_SD() {
	     
	            this.driver = BaseTest.getDriver();
	            this.contactsPage = new Contacts(driver);
	           
			};
			
			
			@Given("the user is on the Dashboard")
			public void the_user_is_on_the_dashboard() {
			    
			}

			@When("User click contacts option on left side panel")
			public void user_click_contacts_option_on_left_side_panel() {
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				
			   contactsPage.Click_Contacts_Link();
			}

			@When("User click on Add_contact button")
			public void user_click_on_add_contact_button() {
				contactsPage.Click_AddContact_Button();
			}

//			@When("I enter First Name,Last Name,Email and Phone Number")
//			public void i_enter_first_name_last_name_email_and_phone_number(io.cucumber.datatable.DataTable dataTable) {
//			    contactsPage.Enter_FirstName(dataTable.cell(1, 0));
//			    contactsPage.Enter_LastName(dataTable.cell(1, 1));
//			    contactsPage.Enter_Email(dataTable.cell(1, 2));
//			    
//			    contactsPage.Enter_PhoneNumber(dataTable.cell(1, 3));
//			    
//			    contactsPage.Select_Country(dataTable.cell(1, 4));
//			}
			
//			@And("I enter First Name,Last Name,Email and Phone Number")
//			public void i_enter_first_name_last_name_email_and_phone_number(DataTable dataTable) {
//
//			    List<List<String>> rows = dataTable.asLists(String.class);
//
//			    for (int i = 1; i < rows.size(); i++) {   // start from 1 to skip header
//
//			        contactsPage.Enter_FirstName(rows.get(i).get(0));
//			        contactsPage.Enter_LastName(rows.get(i).get(1));
//			        contactsPage.Enter_Email(rows.get(i).get(2));
//			        contactsPage.Enter_PhoneNumber(rows.get(i).get(3));
//			        contactsPage.Select_Country(rows.get(i).get(4));
//			        
//			    }
//			}

			        // If submit button exists
			        // contactsPage.clickSubmit();
			    
			@And("I enter user details")
			public void i_enter_user_details() throws Throwable {

			    contactsPage.EnteruserDetails();
			    }
				
			

			@When("I select Country")
			public void I_select_Country(io.cucumber.datatable.DataTable dataTable) throws Throwable {
				
			    contactsPage.Select_Country_FromDropdown(dataTable.cell(1, 0));
			    
			    //Thread.sleep(5000);
			}
			

			@When("I select State")
			public void I_select_State(io.cucumber.datatable.DataTable dataTable) throws Throwable {
			    contactsPage.Select_State_FromDropdown(dataTable.cell(1, 0));
			}

			@When("I select City")
			public void I_select_City(io.cucumber.datatable.DataTable dataTable) throws Throwable {
			    contactsPage.Select_City_FromDropdown(dataTable.cell(1, 0));
			    
			   
			}

			
			  
			  
			  @When("I click Create Contact button") 
			  public void I_click_Create_Contact_button() throws Throwable { 
				  
				  contactsPage.click_CreateContact_Button();
				  
				  
				  
				   }
			  
			  @Then("I should see a success toast message") 
			  public void I_should_see_a_success_toast_message() throws Throwable {
				  
				  String contactsucessmsg= "Contact Created";
				  String Auctal_toastmsg= contactsPage.ContactCreated_ToastMessage();
				  
				 assertEquals(contactsucessmsg, Auctal_toastmsg);
				 
				 
				  
			  
				 //assert.assertequals(contactsucessmsg, Auctal_toastmsg);
				  
				  
			  
			    }
	}


		
			  
			  
			 


