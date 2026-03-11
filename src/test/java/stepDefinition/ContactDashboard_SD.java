package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.When;
import pageFactory.Contact_dashboard;
import pageFactory.Delete_contact_Page;
import pageFactory.Update_Contacts;
import preRequisites.BaseTest;

public class ContactDashboard_SD 

{
	WebDriver driver;
	Contact_dashboard contactDashboard;
	
	
	public ContactDashboard_SD() {
		this.driver = BaseTest.getDriver();
		this.contactDashboard = new Contact_dashboard(driver);
	
		
	}
	
	@When("User click on review duplicate three dot icon")
	public void click_on_threedot_icon() throws Throwable {
		
		contactDashboard.verify_Duplicate_Contact_Added();
	    
	}
	

	@When("User merge the contact")
	public void user_merge_the_contact() throws Throwable {
		contactDashboard.verify_Contact_Merged();
		
	}
	

	@When("User cancel merge contact pop-up")
	public void userCancelsMergePopup() throws Throwable {
		contactDashboard.verify_Contact_pop_up_cancel();
	}


}
