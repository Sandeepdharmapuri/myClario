package pageFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delete_contact_Page {

	
	public WebDriver driver = null;
    private final WebDriverWait wait;
    private Contacts contactsPage; 
    private Update_Contacts updateContactsPage;
    
    
	public Delete_contact_Page(WebDriver driver) {
		
		this.driver = driver;
 	    PageFactory.initElements(driver, this);
 	    // initialize wait after driver is set to avoid NPE
 	   contactsPage = new Contacts(driver);
 	   
 	    this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
 	    
 	    
}
	@FindBy(xpath = "//div[normalize-space()='Delete']")
	    WebElement Delete_Button_InDropdown;
	
	@FindBy(xpath = "//div[@class='flex flex-col-reverse sm:flex-row sm:justify-end sm:space-x-2']/button[normalize-space()='Delete']")
	WebElement Delete_Button_InConfirmationPopup;
	
	
	
	public void click_Delete_Button_InDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Delete_Button_InDropdown)).click();
	}
		
	public void click_Delete_Button_InConfirmationPopup() {
		wait.until(ExpectedConditions.elementToBeClickable(Delete_Button_InConfirmationPopup)).click();
	}
		
		
	public void deleteContact() {
		
		updateContactsPage.click_Edit_Button_FirstRow();		

		click_Delete_Button_InDropdown();
		click_Delete_Button_InConfirmationPopup();
		
	}
		
	
		
	
}