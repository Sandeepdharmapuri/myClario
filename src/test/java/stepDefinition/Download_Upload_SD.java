package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class Download_Upload_SD {
	
	private WebDriver driver;
	private pageFactory.Download_upload_page Download_upload_page;
	
	public Download_Upload_SD() {
		this.driver = preRequisites.BaseTest.getDriver();
		this.Download_upload_page = new pageFactory.Download_upload_page(driver);
	}

	
	@When("User click on download template button")
	public void user_click_on_download_template_button() {
		
		Download_upload_page.click_Download_Template_Button();
		
	    
		
	}

	@Then("I should see a success toast message as file download")
	public void i_should_see_a_success_toast_message_as_file_download() {
		
		Download_upload_page.verify_Template_Download_Success_Toast_Message();
	    
	}
	

	@When("User clicks on upload template button")
	public void user_Clicks_On_Upload_Template_Button() {
		Download_upload_page.click_Upload_Excel_Button();
		// select_source() method now handles dropdown click and file selection
		Download_upload_page.select_source();
	}
	
	@Then("I should see a success toast message as file upload")
	public void i_should_see_a_success_toast_message_as_file_upload() {
		
		Download_upload_page.verify_Template_Download_Success_Toast_Message();
	    
	}

}
