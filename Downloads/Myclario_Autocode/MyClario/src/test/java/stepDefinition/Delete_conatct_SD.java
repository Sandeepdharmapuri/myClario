package stepDefinition;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pageFactory.Delete_contact_Page;
import pageFactory.Update_Contacts;
import preRequisites.BaseTest;

public class Delete_conatct_SD {
	
	WebDriver driver;
	Update_Contacts updateContactsPage;
	Delete_contact_Page deleteContactPage;
	
	public Delete_conatct_SD() {
		this.driver = BaseTest.getDriver();
		this.updateContactsPage = new Update_Contacts(driver);
		this.deleteContactPage = new Delete_contact_Page(driver);
		
	}
	
	@When("Click on Delete button")
	public void click_on_delete_button() {
		
		deleteContactPage.click_Delete_Button_InDropdown();
	    
	}

	@And("Click on final delete")
	public void click_on_final_delete() {
		
		deleteContactPage.click_Delete_Button_InConfirmationPopup();
	    
	}

}
