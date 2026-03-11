package pageFactory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Contact_dashboard {

    public WebDriver driver = null;
    private final WebDriverWait wait;
    private Contacts contactsPage;  // ✅ Fixed: was "__contactsPage__", assigned as "contactsPage"

    public Contact_dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.contactsPage = new Contacts(driver);  // ✅ Fixed: added "this."
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> rows;

    @FindBy(xpath = "//div[contains(text(),'Review Duplicate')]")
    WebElement Review_Duplicate_option;

    @FindBy(xpath = "//button[normalize-space(text())='Keep as Unique']")
    WebElement Keep_as_Unique_Button;

    @FindBy(xpath = "(//div[@class='grid gap-1']/div[contains(text(),'Contact Updated')])[2]")
    WebElement Contact_Unique_Toast_Message;
    
    @FindBy(xpath = "//button[contains(text(),'Merge')]")
    WebElement Merge_Button;
    
    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement Cancel_Button;


    public boolean verify_Duplicate_Contact_Added() {

        boolean anyReviewFound = false;
        int maxAttempts = 10;  // ✅ Added: Prevent infinite loops
        int attempts = 0;

        while (attempts < maxAttempts) {

            attempts++;

            // ✅ Re-fetch rows every iteration
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

            boolean foundReview = false;
            for (WebElement row : rows) {

                if (row.getText().contains("Review")) {

                    foundReview = true;
                    anyReviewFound = true;

                    WebElement threeDotButton = row.findElement(
                            By.xpath(".//button[.//span[text()='Open menu']]"));

                    threeDotButton.click();

                    wait.until(ExpectedConditions.elementToBeClickable(Review_Duplicate_option)).click();

                    wait.until(ExpectedConditions.elementToBeClickable(Keep_as_Unique_Button)).click();

                    // Wait for toast appear then disappear
                    wait.until(ExpectedConditions.visibilityOf(Contact_Unique_Toast_Message));
                    wait.until(ExpectedConditions.invisibilityOf(Contact_Unique_Toast_Message));

                    break;
                }
            }
            
            // ✅ Exit loop if no review found in this iteration
            if (!foundReview) {
                break;
            }
        }

        return anyReviewFound;
    }
    
	public boolean verify_Contact_Merged() throws Throwable {
		boolean anyReviewFound = false;
		int maxAttempts = 10; // ✅ Added: Prevent infinite loops
		int attempts = 0;

		while (attempts < maxAttempts) {

			attempts++;

			// ✅ Re-fetch rows every iteration
			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
			
			boolean foundReview = false;

			for (int i = 0; i < rows.size(); i++) {
				// ✅ Re-fetch rows inside the loop to avoid stale references
				List<WebElement> currentRows = driver.findElements(By.xpath("//table/tbody/tr"));
				
				// ✅ Check if index is within bounds before accessing
				if (i >= currentRows.size()) {
					break;
				}
				
				WebElement table_row = currentRows.get(i);

				if (table_row.getText().contains("Review")) {

					foundReview = true;
					anyReviewFound = true;

					WebElement threeDotButton = table_row
							.findElement(By.xpath(".//button[.//span[text()='Open menu']]"));

					threeDotButton.click();
					wait.until(ExpectedConditions.elementToBeClickable(Review_Duplicate_option)).click();
					wait.until(ExpectedConditions.elementToBeClickable(Merge_Button)).click();
					
					// ✅ Wait for the row to be removed from the table
					wait.until(ExpectedConditions.stalenessOf(table_row));

					break; // Exit loop after processing one merge
				}
			}
			
			// ✅ Exit outer loop if no review found in this iteration
			if (!foundReview) {
				break;
			}
		}
	return anyReviewFound;
}
    
	  public void click_Merge_Button() {
			wait.until(ExpectedConditions.elementToBeClickable(Merge_Button)).click();
	    }
	    
	    public void click_Cancel_Button() {
	    			wait.until(ExpectedConditions.elementToBeClickable(Cancel_Button)).click();
	    }
	    
		public boolean verify_Contact_pop_up_cancel() throws Throwable {
			boolean anyReviewFound = false;
			int maxAttempts = 10; // ✅ Added: Prevent infinite loops
			int attempts = 0;

			while (attempts < maxAttempts) {

				attempts++;

				// ✅ Re-fetch rows every iteration
				List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
				
				boolean foundReview = false;

				for (int i = 0; i < rows.size(); i++) {
					// ✅ Re-fetch rows inside the loop to avoid stale references
					List<WebElement> currentRows = driver.findElements(By.xpath("//table/tbody/tr"));
					
					// ✅ Check if index is within bounds before accessing
					if (i >= currentRows.size()) {
						break;
					}
					
					WebElement table_row = currentRows.get(i);

					if (table_row.getText().contains("Review")) {

						foundReview = true;
						anyReviewFound = true;

						WebElement threeDotButton = table_row
								.findElement(By.xpath(".//button[.//span[text()='Open menu']]"));

						threeDotButton.click();
						wait.until(ExpectedConditions.elementToBeClickable(Review_Duplicate_option)).click();
						wait.until(ExpectedConditions.elementToBeClickable(Cancel_Button)).click();
						
						// ✅ Wait for the row to be removed from the table
						//wait.until(ExpectedConditions.stalenessOf(table_row));

						break; // Exit loop after processing one merge
					}
				}
				
				// ✅ Exit outer loop if no review found in this iteration
				if (!foundReview) {
					break;
				}
			}
		return anyReviewFound;
	}
	    

					
	    
}
