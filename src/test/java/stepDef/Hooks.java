package stepDef;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.Scenario; // Import the Scenario class
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import pages.Config_Reader;
import pages.WebDriverManager;

import java.io.IOException;

public class Hooks {
    private WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        driver = WebDriverManager.getDriver();
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Config_Reader.properties("extent.report.path"));
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("user", System.getProperty("user.name"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Device", "Chrome");
        }
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown() throws IOException {
        if (scenario.isFailed()) {
            test.fail("Scenario Failed");
        }
        WebDriverManager.quitDriver();
        if (extent != null) {
            extent.flush();
        }
    }
}