package runner;


//import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
  features = "src/test/resources/Feature/Download_Upload_Template.feature",
  glue = {"stepDefinition", "preRequisites"},
 // tags = "@TC_03",
  plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests 
{
	

	
}


