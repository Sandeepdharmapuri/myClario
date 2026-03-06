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


    public boolean verify_Duplicate_Contact_Added() throws Throwable {

        boolean anyReviewFound = false;
        boolean reviewFound = true;

        while (reviewFound) {
            reviewFound = false;

            // ✅ Re-fetch rows every iteration to avoid StaleElementReferenceException
       
            for (WebElement row : rows) {

                if (row.getText().contains("Review")) {
                    reviewFound = true;
                    anyReviewFound = true;

                    // ✅ Click the three-dot menu button in this specific row
                    WebElement threeDotButton = row.findElement(
                        By.xpath(".//button[.//span[text()='Open menu']]")
                    );
                    threeDotButton.click();

                    // ✅ Wait for the dropdown option to be visible before clicking
                    wait.until(ExpectedConditions.visibilityOf(Review_Duplicate_option));
                    Review_Duplicate_option.click();

                    // ✅ Wait for the button to be clickable before clicking
                    wait.until(ExpectedConditions.elementToBeClickable(Keep_as_Unique_Button));
                    Keep_as_Unique_Button.click();

                    // ✅ Wait for toast to appear first, then disappear
                    wait.until(ExpectedConditions.visibilityOf(Contact_Unique_Toast_Message));
                    wait.until(ExpectedConditions.invisibilityOf(Contact_Unique_Toast_Message));

                    // ✅ Break to re-fetch rows after DOM update
                    break;
                }
            }
        }

        return anyReviewFound;
    }
}