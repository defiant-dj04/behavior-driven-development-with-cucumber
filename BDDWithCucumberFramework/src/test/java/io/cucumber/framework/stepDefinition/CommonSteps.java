package io.cucumber.framework.stepDefinition;

import io.cucumber.framework.utility.Core;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CommonSteps {
    Core core = new Core();
    WebDriver driver;

    @Given("The browser is open")
    public void the_browser_is_open() throws IOException {
        driver = core.setUp();
    }

    @Given("The browser window is maximized")
    public void the_browser_window_is_maximized() {
        driver.manage().window().maximize();
    }

    @Then("Closes the browser")
    public void closes_the_browser() {
        core.tearDown();
    }
}
