package pageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import preRequisites.ExcelReader;

public class Update_Contacts {
	
	public WebDriver driver = null;
    private final WebDriverWait wait;
    private Contacts contactsPage; 
    
	public Update_Contacts(WebDriver driver) {
		
		this.driver = driver;
 	    PageFactory.initElements(driver, this);
 	    // initialize wait after driver is set to avoid NPE
 	   contactsPage = new Contacts(driver);
 	    this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "(//table[@class='w-full table-fixed']//button[starts-with(@id,'radix')])[1]")
	WebElement Edit_Button_FirstRow;
	
	@FindBy(xpath = "//div[normalize-space()='Edit']")
	WebElement Edit_Button_InDropdown;
	
	@FindBy(xpath = "//button[normalize-space()='Select source']")
	WebElement Source_Dropdown;
	
	@FindBy(xpath = "//div[@class='space-y-2']/label[@class='text-sm font-medium']/following-sibling::button")
	WebElement Edit_Source_Dropdown;
	
	@FindBy(xpath = "//button[normalize-space()='Select source']/following-sibling::select[@tabindex='-1'][@aria-hidden='true']")
	WebElement Source_Dropdown_Options;
	
	By overlay = By.xpath("//div[contains(@class,'fixed inset-0')]");
	
	@FindBy(xpath = "(//li[@role='status']//div[normalize-space()='Contact Updated'])[2]")
	WebElement Contact_Updated_Toast_Message;
//	@FindBy(xpath = "//button[normalize-space()='Select source']")
//	WebElement Source_Dropdown;

	
	public void click_Edit_Button_FirstRow() {
		wait.until(ExpectedConditions.elementToBeClickable(Edit_Button_FirstRow)).click();
	}
	
	public void click_Edit_Button_InDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Edit_Button_InDropdown)).click();
	}
	
	public void verify_Edit_Contact_Form_Displayed() {
		wait.until(ExpectedConditions.visibilityOf(contactsPage.FirstName_Field));
	}
	
	public void edit_last_name() throws InterruptedException {
		ExcelReader ER = new ExcelReader("excel.filepath3");
	    List<Map<String, String>> data = ER.getSheetDataAsMap("Update_contact");

	    if (data.isEmpty()) {
	        System.out.println("No data found in the 'Update_contact' sheet.");
	        return;
	    }

	    for (Map<String, String> rowData : data) {

	        System.out.println("Available keys in rowData: " + rowData.keySet());

	        String lastNameKey = rowData.getOrDefault("Lastname", "").trim();
	        String Source = rowData.getOrDefault("Source", "").trim();

	        contactsPage.LastName_Field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	        contactsPage.LastName_Field.sendKeys(lastNameKey);

	        //Thread.sleep(5000);
//	        // Scroll into view
//	        ((JavascriptExecutor) driver).executeScript(
//	                "arguments[0].scrollIntoView({block: 'center'});", Source_Dropdown);
//	        
//	        
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
//
	        // Wait until clickable
	        WebElement dropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(Edit_Source_Dropdown));

	     // Click using JS (more reliable for Radix UI)
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", dropdown);
//
//	        // Select option
//	        Select select = new Select(Source_Dropdown);
//	        select.selectByVisibleText(Source);
//	        
//	       // dropdown.click();
//
//	     // Wait and select option from dropdown list
//	     WebElement sourceOption = wait.until(ExpectedConditions.elementToBeClickable(
//	             By.xpath("//div[@role='option'&normalize-space()='" + Source + "']")));
//
//	     sourceOption.click();
	        
	     // Scroll into view
//	        ((JavascriptExecutor) driver).executeScript(
//	                "arguments[0].scrollIntoView({block: 'center'});", Source_Dropdown);

	        // Wait visible
	        wait.until(ExpectedConditions.visibilityOf(Edit_Source_Dropdown));

	        // Click using JS (important)
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", Edit_Source_Dropdown);

	        // Wait option and click
	        WebElement sourceOption = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@role='option' and normalize-space()='" + Source + "']")));

	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", sourceOption);

	        click_Update_Contact_Button();

	        wait.until(ExpectedConditions.invisibilityOf(Contact_Updated_Toast_Message));
	        
	        click_Edit_Button_FirstRow();
	        click_Edit_Button_InDropdown();
	        
	        
	        
	    }

	     
	        
	        

	}
	
	
//	public void edit_source(String newSource) {
//		wait.until(ExpectedConditions.elementToBeClickable(Source_Dropdown)).click();
//		WebElement sourceOption = wait.until(ExpectedConditions.elementToBeClickable(
//				By.xpath("//div[@role='option' and text()='" + newSource + "']")));
//		sourceOption.click();
	//}
	
	public void click_Update_Contact_Button() {
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[normalize-space()='Update Contact']")));
		updateButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(Contact_Updated_Toast_Message));
		

		// Pause for a bit even after it is visible
		try {
		    Thread.sleep(2000); // 2 seconds
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		}

	}
}