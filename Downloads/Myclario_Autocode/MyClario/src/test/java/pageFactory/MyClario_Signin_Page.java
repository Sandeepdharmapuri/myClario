
package pageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import preRequisites.ExcelReader;

public class MyClario_Signin_Page {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MyClario_Signin_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = "#email")
    private WebElement Email_Field;

    @FindBy(css = "#password")
    private WebElement Password_Field;

    @FindBy(xpath = "//button[normalize-space()='Sign In' or @type='submit']")
    private WebElement SignIn_Button;

    @FindBy(xpath = "//button[contains(text(),'Forgot Password?')]")
    private WebElement ForgotPassword_Link;

    @FindBy(xpath = "//button[normalize-space()=\"Don't have an account? Sign up\"]")
    private WebElement SignUpPage_Button_UnderSignIn;
    
    @FindBy(xpath = "//span[text()='Dashboard']")
    private WebElement Dashboard_Page;
    
    @FindBy(xpath = "(//li[@role='status']/div/div[normalize-space()='Welcome!'])[1]")
    private WebElement Login_Welcome_Message;
    
    @FindBy(xpath = "(//div[@class='grid gap-1']/following-sibling::button)[1]")
    private WebElement Signin_welcome_message_close_btn;

    // === Public API used by Step Definitions ===

    public void enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(Email_Field));
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.visibilityOf(Password_Field));
        el.clear();
        el.sendKeys(password);
    }

    public void Clicksignin_Btn() {
        wait.until(ExpectedConditions.elementToBeClickable(SignIn_Button)).click();
    }

    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(ForgotPassword_Link)).click();
    }

    public void clickSignUpUnderSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(SignUpPage_Button_UnderSignIn)).click();
        
    }
    public String dashboard() {
		wait.until(ExpectedConditions.visibilityOf(Dashboard_Page));
		return Dashboard_Page.getText();
    }
		
	public void sucessfullmessage() {
		wait.until(ExpectedConditions.invisibilityOf(Login_Welcome_Message));
	}
	
	public void close_Signin_Welcome_Message() {
		wait.until(ExpectedConditions.elementToBeClickable(Signin_welcome_message_close_btn)).click();
	}
	
	
    /**
     * Convenience method when the step provides a DataTable with headers:
     * | username | password |
     */
//    public void loginWithCredentials(DataTable dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        if (rows.isEmpty()) {
//            throw new IllegalArgumentException("Credentials table is empty.");
//        }
//        Map<String, String> row = rows.get(0);
//
//        String username = row.getOrDefault("username", "").trim();
//        String password = row.getOrDefault("password", "").trim();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            throw new IllegalArgumentException("Username and Password must be provided in the table.");
//        }
//
//        // Actually enter the credentials
//        enterEmail(username);
//        enterPassword(password);
//    }
    
    
    
    public void EnterEmailandPassword() {

        ExcelReader ER = new ExcelReader("excel.filepath1");
        List<Map<String, String>> data = ER.getSheetDataAsMap("Login");

        if (data.isEmpty()) {
            System.out.println("No data found in the 'Login' sheet.");
            return;
        }

        for (Map<String, String> rowData : data) {

            System.out.println("Available keys in rowData: " + rowData.keySet());

            String EmailID = rowData.getOrDefault("EmailID", "").trim();
            String Password = rowData.getOrDefault("Password", "").trim();

            //  Clear & Enter Email
            if (!EmailID.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(Email_Field));
                Email_Field.click();
                Email_Field.sendKeys(Keys.CONTROL + "a");
                Email_Field.sendKeys(Keys.DELETE);
                Email_Field.sendKeys(EmailID);
            } else {
                System.out.println("EmailID is empty in this row.");
            }

            //  Clear & Enter Password
            if (!Password.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(Password_Field));
                Password_Field.click();
                Password_Field.sendKeys(Keys.CONTROL + "a");
                Password_Field.sendKeys(Keys.DELETE);
                Password_Field.sendKeys(Password);
            } else {
                System.out.println("Password is empty in this row.");
            }

            // 🔹 Click Login
           // Clicksignin_Btn();

            // 🔹 Wait for login attempt to complete (error / page reload)
//            wait.until(ExpectedConditions.or(
//                    ExpectedConditions.visibilityOf(Email_Field),
//                    ExpectedConditions.visibilityOf(Password_Field)
           // ));
        }
    }

}
