package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.Myclario_LandingPage;
import preRequisites.BaseTest;


public class Myclario_LandingPage_SD {

    private WebDriver driver;
    private Myclario_LandingPage landingPage;

    public Myclario_LandingPage_SD() {
     {
            this.driver = BaseTest.getDriver();
            this.landingPage = new Myclario_LandingPage(driver);
        };
    }

    @Given("the user is on the landing page")
    public void the_user_is_on_the_landing_page() 
    {
       landingPage.verify_LandingPage_Card1_Text();
    }

    @When("the user clicks the Get Started button")
    public void the_user_clicks_the_button() {
		landingPage.click_GetStarted_Button();
		landingPage.verify_SignInPage_Heading();
	}
    
  

    @Then("the user should be redirected to the Sign-in page")
    public void the_user_should_be_redirected_to_the_sign_in_page() {
        landingPage.verify_SignInPage_Heading();
    }
}
