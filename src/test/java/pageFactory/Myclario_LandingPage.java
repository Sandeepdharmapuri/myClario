package pageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import preRequisites.ExcelReader;

public class Myclario_LandingPage 
{
	  private WebDriverWait wait;

	   public Myclario_LandingPage(WebDriver driver) 
	  	{
	  		this.driver = driver;
	  	    PageFactory.initElements(driver, this);
	  	}
	  	 private WebDriver driver;
	
	  	 @FindBy(xpath = "//button[normalize-space()='Get Started']")
	  	 WebElement GetStartedButton;
	  	 
	  	 @FindBy(xpath = "//h3[normalize-space()=\"Sign In\"]")
	  	 WebElement SignInPage_Heading;
	  	 
	  	@FindBy(xpath = "//button[(text()='Create Account')]")
	  	 WebElement CreateAccountButton;
	  	
	  	@FindBy(xpath = "//h3[(text()='End-to-End Security')]")
	  	 WebElement LandingPage_card1_Text;
	  	
	  
	  	
	  	public void click_GetStarted_Button() 
	  	 {
	  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  	   WebElement getStartedBtn = wait.until(ExpectedConditions.elementToBeClickable(GetStartedButton));
	  	   getStartedBtn.click();
	  	 }
	     
	  	public void click_CreateAccount_Button() 
	  	 {
	  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  	   WebElement createAccountBtn = wait.until(ExpectedConditions.elementToBeClickable(CreateAccountButton));
	  	   createAccountBtn.click();
	  	 }
	  	 
	  	 public void verify_LandingPage_Card1_Text()
	  	 {
	  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  	   WebElement card1TextElement = wait.until(ExpectedConditions.visibilityOf(LandingPage_card1_Text));
	  	   String actualText = card1TextElement.getText();
	  	   String expectedText = "End-to-End Security";
	  	   Assert.assertEquals(actualText, expectedText, "Card 1 text does not match the expected value.");
	  	 }
	  	 
	  	 public void verify_SignInPage_Heading()
	  	 {
	  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  	   WebElement signInHeadingElement = wait.until(ExpectedConditions.visibilityOf(SignInPage_Heading));
	  	   String actualText = signInHeadingElement.getText();
	  	   String expectedText = "Sign In";
	  	   Assert.assertEquals(actualText, expectedText, "Sign In page heading does not match the expected value.");
	  	 }
	  	

}

