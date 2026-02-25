package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageFactory.Update_Contacts;
import preRequisites.BaseTest;

public class UpdateContact_SD {

	
	WebDriver driver;
	Update_Contacts updateContactsPage;
	
	public UpdateContact_SD() {
		this.driver = BaseTest.getDriver();
		this.updateContactsPage = new Update_Contacts(driver);
		
	}
	@When("User click on three dots and Edit button of contact")
	public void user_click_on_three_dots_and_edit_button_of_contact() {
	    updateContactsPage.click_Edit_Button_FirstRow();
	   
	  //  updateContactsPage.verify_Edit_Contact_Form_Displayed();
	}

	@When("Update Last Name")
	public void update_last_name() throws InterruptedException {
		updateContactsPage.click_Edit_Button_InDropdown();
	    updateContactsPage.edit_last_name();
	}

//	@When("Select source {string}")
//	public void select_source(String newSource) {
//	    updateContactsPage.edit_source(newSource);
//	}

	@When("Click on Update contact button")
	public void click_on_update_contact_button() {
			   
	    updateContactsPage.click_Update_Contact_Button();
	    
	}
	


	
}
