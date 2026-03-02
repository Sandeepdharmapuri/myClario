package runner;


//import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
  features = "src/test/resources/Feature",
  glue = {"stepDefinition", "preRequisites"},
  //tags = "SigninPage or @AddContact or @UpdateContact or @DeleteContact",
  plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Myrunner extends AbstractTestNGCucumberTests 
{
	
}
