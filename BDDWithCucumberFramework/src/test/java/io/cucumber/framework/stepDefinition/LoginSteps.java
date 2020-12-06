package io.cucumber.framework.stepDefinition;

import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.LoginPage;
import io.cucumber.framework.utility.Core;
import io.cucumber.framework.utility.FileHandler;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class LoginSteps {
    Core core = new Core();
    FileHandler fileHandler = new FileHandler();
    WebDriver driver = core.getDriver();
    LoginPage login = PageFactory.initElements(driver, LoginPage.class);
    EnterTimeTrackPage enterTimeTrack;

    @When("The user is in actiTIME login page and the page title is {string}")
    public void the_user_is_in_acti_time_login_page_and_the_page_title_is(String expectedTitle) throws IOException {
        String url = core.getEnvironmentURL();
        driver.get(url);
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Then("The user enters username and password into the respective fields")
    public void the_user_enters_username_and_password_into_the_respective_fields() throws IOException {
        String username = fileHandler.getDataFromProperties("username").trim();
        String password = fileHandler.getDataFromProperties("password").trim();

        if (username == null || username.equals("") || password == null || password.equals(""))
            throw new IOException("Key-value pair is not set, check 'appConfig.properties' file");

        login.enterUsername(username);
        login.enterPassword(password);
    }

    @Then("The user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        login.clickOnLoginBtn();
    }

    @Then("The user should redirect to the home page and page title should be {string}")
    public void the_user_should_redirect_to_the_home_page_and_page_title_should_be(String expectedTitle) throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), expectedTitle);

        enterTimeTrack = PageFactory.initElements(driver, EnterTimeTrackPage.class);
    }

    @Then("The user clicks on the logout button")
    public void the_user_clicks_on_the_logout_button() {
        enterTimeTrack.clickOnLogoutBtn();
    }

//    WebDriver driver;
//    FileLib fLib = new FileLib();
//    CoreLib cLib = new CoreLib();
//    String username, password;
//
//    @Given("The browser is open")
//    public void the_browser_is_open() throws IOException {
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(Boolean.parseBoolean(fLib.getDataFromProperties("headlessMode" )));
//
////        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        cLib.setElementPageScriptLoadTimeout(driver, 10, 30, 20);
//    }
//
//    @Given("The user have the valid login credentials")
//    public void the_user_have_the_valid_login_credentials() throws IOException {
//        username = fLib.getDataFromProperties("username" );
//        password = fLib.getDataFromProperties("password" );
//    }
//
//    @When("The user is in actiTIME login page")
//    public void the_user_is_in_acti_time_login_page() throws IOException {
//        driver.get(fLib.getDataFromProperties("url" ));
//        Assert.assertEquals(driver.getTitle(), "actiTIME - Login");
//    }
//
//    @Then("The user need to enter the valid username and password in the respective fields")
//    public void the_user_need_to_enter_the_valid_username_and_password_in_the_respective_fields() {
//        driver.findElement(By.id("username")).sendKeys(username);
//        driver.findElement(By.name("pwd")).sendKeys(password);
//    }
//
//    @Then("The user need to click on the login button")
//    public void the_user_need_to_click_on_the_login_button() {
//        driver.findElement(By.xpath("//div[text()='Login ']")).click();
//    }
//
//    @Then("The user need to verify navigation to the home page")
//    public void the_user_need_to_verify_navigation_to_the_home_page() throws InterruptedException {
//        Thread.sleep(5000 );
//        Assert.assertEquals(driver.getTitle(), "actiTIME - Enter Time-Track");
//    }
//
//    @Then("The user closes the browser")
//    public void the_user_closes_the_browser() {
//        if (driver != null)
//            driver.quit();
//    }
}