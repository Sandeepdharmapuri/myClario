package preRequisites;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static WebDriver driver;
    private static Properties pr = new Properties();

    // Ensure this hook runs before step-level hooks so the WebDriver is initialized
    @Before(order = 0)
    public void setUp() throws IOException {

        // Load config file
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
        pr.load(fis);

        // Launch browser
        if (pr.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (pr.getProperty("browser").equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        driver.get(pr.getProperty("MyClario_PROD"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}