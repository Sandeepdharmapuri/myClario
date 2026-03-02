package stepDefinition;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageFactory.MyClario_Signin_Page;
import preRequisites.BaseTest;

public class MyClario_signin_Page_SD {

    private WebDriver driver;
    private MyClario_Signin_Page signinPage;
    private Myclario_LandingPage_SD landingPageSD;

    // Ensure this init runs after BaseTest's setup (order > 0)
    @Before(order = 1)
    public void init() {
        this.driver = BaseTest.getDriver();
        this.signinPage = new MyClario_Signin_Page(driver);
    }

//    @When("I enter email and password")
//    public void i_enter_email_and_password(DataTable dataTable) {
//        // Uses the DataTable provided under the step in the feature file
//        signinPage.loginWithCredentials(dataTable);
//    }

    @When("I enter email and password")
    public void i_enter_email_and_password() {
        // Uses the DataTable provided under the step in the feature file
        signinPage.EnterEmailandPassword();
    } 
    
    @When("I click on Sign In button")
    public void i_click_on_sign_in_button() {
        signinPage.Clicksignin_Btn();
        //signinPage.close_Signin_Welcome_Message();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        // Wait until URL contains dashboard (replace with a page marker if possible)
        new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(300))
            .ignoring(Exception.class)
            .until(d -> driver.getCurrentUrl().toLowerCase().contains("dashboard"));
        

//        assertTrue(("Expected URL to contain 'dashboard' but got: " + driver.getCurrentUrl()),
//				driver.getCurrentUrl().toLowerCase().contains("dashboard"));
        
        
       
        
        String Page_Title= signinPage.dashboard();
        String expectedUrlSubstring = "Dashboard";
        System.out.println(expectedUrlSubstring);
        
        Assert.assertEquals(Page_Title, expectedUrlSubstring);
				
            
    }
}