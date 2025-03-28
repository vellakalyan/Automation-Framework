package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/OnlineOrder.feature",
        glue = "stepDef",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/feature-runs/online-order-report.html",
                "json:target/cucumber-reports/feature-runs/online-order-report.json",
                "junit:target/cucumber-reports/feature-runs/online-order-report.xml"
        },
        monochrome = true,
        dryRun = false
        // tags = "@OrderPlacement"
)
public class TestRunner {
    // This class is intentionally left empty. Cucumber options are defined above.
}