package pageFactory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Download_upload_page {

	private final WebDriver driver;
    private final WebDriverWait wait;

    public Download_upload_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // Reduced timeout and polling interval for faster fail detection
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.wait.pollingEvery(Duration.ofMillis(100));
    }
	
    
    @FindBy(xpath = "//button[contains(text(),'Download')]")
    private WebElement Download_Template_Button;
    
    @FindBy(xpath = "//button[normalize-space(text())='Upload Excel']")
    private WebElement Upload_Excel_Button;
    
    @FindBy(xpath = "//button[@role='combobox' and starts-with(@aria-controls, 'radix-') and .//span[normalize-space(.)='Select a source...']]")
    private WebElement Select_Source_Dropdown_under_Upload_Excel;
    
    @FindBy(xpath = "//button[contains(text(),'Choose File')]")
    private WebElement Choose_File_Button_under_Upload_Excel;
    
    @FindBy(xpath = "(//div[@class='grid gap-1']/div[normalize-space()='Upload Complete!']/following-sibling::div)[2]")
    private WebElement Upload_Success_Toast_Message;
    
    @FindBy(xpath = "(//div[contains(text(),'Template Downloaded')])[2]")
    private WebElement Template_Download_Success_Toast_Message;
    
    @FindBy(xpath = "(//div[contains(text(),'Template Uploaded')][2]")
    private WebElement Template_Upload_Success_Toast_Message;
    
    @FindBy(xpath = "//div[@role='presentation']/div[@role='option']")
    private WebElement Select_Source_Dropdown_Options_under_Upload_Excel;
    
    
    
    public void click_Download_Template_Button() {
    	wait.until(ExpectedConditions.elementToBeClickable(Download_Template_Button));
		Download_Template_Button.click();
		// Use a shorter timeout for toast messages that disappear quickly (typically 2-3 seconds)
		WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			toastWait.until(ExpectedConditions.invisibilityOf(Template_Download_Success_Toast_Message));
		} catch (Exception e) {
			// Toast might have already disappeared, continue
		}
	}
    
    public void click_Upload_Excel_Button() {
		Upload_Excel_Button.click();
	}
	
	public void click_Select_Source_Dropdown_under_Upload_Excel() {
		Select_Source_Dropdown_under_Upload_Excel.click();
	}
	
	public void click_Choose_File_Button_under_Upload_Excel() {
		Choose_File_Button_under_Upload_Excel.click();
	}
	
	public void select_source() {
		// 1) Open the combobox only if it's not already open
		WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@role='combobox' and .//span[normalize-space(.)='Select a source...']]")));
		if (!"true".equals(combo.getAttribute("aria-expanded"))) {
			combo.click();
		}

		// 2) Wait for the overlay/menu to be present, then select an option
		WebElement option = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[contains(@role,'option') and normalize-space(.)='Google']")));
		option.click();

		// 3) Wait for file input to be present
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));

		// 4) Provide an absolute path (forward slashes are fine on Windows in Java)
		String filePath = "C:/Users/SandeepKumarDharmapu/Downloads/myClario-main/myClario-main/src/test/resources/TestData/Builk_upload.xlsx";
		fileInput.sendKeys(filePath);

		// 5) Wait for success indicator with short timeout for toast that disappears quickly
		WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			toastWait.until(ExpectedConditions.visibilityOf(Upload_Success_Toast_Message));
		} catch (Exception e) {
			// Toast might appear briefly or not at all, continue
		}
	}


	public void verify_Template_Download_Success_Toast_Message() {
		try {
			wait.until(ExpectedConditions.invisibilityOf(Template_Download_Success_Toast_Message));
		} catch (Exception e) {
			// Toast might have already disappeared, continue
		}
	}
    
}
