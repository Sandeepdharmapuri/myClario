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

}
