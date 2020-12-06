package io.cucumber.framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:feature/login.feature",       // The path of the feature files
    glue = { "io.cucumber.framework.stepDefinition" },  // The path of step definition files
    dryRun = false,                                     // To check the mapping is proper between feature file and step
    monochrome = true,                                  // To display the console output in an proper readable format eliminating special characters
    plugin = {                                          // To generate different types of reporting
        "pretty",
        "html:target/cucumber-reports/test-results.html",
        "json:target/cucumber-reports/test-results.json",
        "junit:target/cucumber-reports/test-results.xml"
    }
)
public class TestRunnerJUnit {

}