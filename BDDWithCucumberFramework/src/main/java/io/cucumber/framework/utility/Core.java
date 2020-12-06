package io.cucumber.framework.utility;

import io.cucumber.framework.base.DefaultConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * It is collection of commonly used methods throughout the framework.
 *
 * @author Deepjyoti Barman
 * @since August 02, 2020
 */
public class Core implements DefaultConstants {
    // Tip: Enable the "Add unambiguous imports on the fly" in Settings > Editor > General > Auto Import, IntelliJ IDEA will add them as you type without the need for any shortcuts.
    // Note: WebDriver has to be static to have global scope or else we will get NullPointerException
    static WebDriver driver;
    FileHandler fileHandler = new FileHandler();


    /**
     * Set the waiting time for loading a page, loading an element and loading a script.
     *
     * @param driver Current driver reference in use
     */
    public void setElementPageScriptLoadTimeout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
    }


    /**
     * Set the waiting time for loading a page, loading an element and loading a script taking parameters from user.
     *
     * @param driver          Current driver reference in use
     * @param iwTimeoutInSecs Implicit wait timeout in seconds
     * @param plTimeoutInSecs Page load timeout in seconds
     * @param sTimeoutInSecs  Script timeout in seconds
     */
    public void setElementPageScriptLoadTimeout(WebDriver driver, long iwTimeoutInSecs, long plTimeoutInSecs, long sTimeoutInSecs) {
        driver.manage().timeouts().implicitlyWait(iwTimeoutInSecs, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(plTimeoutInSecs, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(sTimeoutInSecs, TimeUnit.SECONDS);
    }

    /**
     * Return the url of the application to be loaded watching out the configuration from 'appConfig.properties' file
     *
     * @return The url of the application to be loaded
     * @throws IOException
     */
    public String getEnvironmentURL() throws IOException {
        String environment = fileHandler.getDataFromProperties("environment").trim();

        if (environment == null || environment.equals(""))
            throw new IOException("Key-value pair is not set, check 'appConfig.properties' file");
        else if (environment.equalsIgnoreCase("actitime-demo"))
            // Return the online demo application page of actiTIME
            return "https://demo.actitime.com/login.do";
        else if (environment.equalsIgnoreCase("actitime-localhost"))
            // Return the application page of actiTIME running locally
            return "http://localhost:2222/login.do";
        else
            // Return the production page of actiTIME
            return "https://www.actitime.com/";
    }


    /**
     * Configure the browser, launch it and set up the driver timeouts
     *
     * @return Current driver reference in use
     * @throws IOException
     */
    public WebDriver setUp() throws IOException {
        String browser = fileHandler.getDataFromProperties("browser").trim().toLowerCase();

        if (browser == null || browser.equals(""))
            throw new IOException("Key-value pair is not set, check 'appConfig.properties' file");

        switch ((browser)) {
            case "chrome":
                // Configuring Google Chrome browser before launch
                HashMap<String, Object> chromePrefs = new HashMap<>();

                chromePrefs.put("profile.default_content_settings.popups", 0);
                // Pass the argument 1 to allow and 2 to block
                chromePrefs.put("profile.default_content_setting_values.notifications", 1);
                chromePrefs.put("download.default_directory", DEFAULT_DOWNLOAD_DIR_PATH);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("safebrowsing.enabled", false);

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
//                options.addArguments("--disable-notifications");
                options.addArguments("--disable-websecurity");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("--enable-javascript");

                String headlessMode = fileHandler.getDataFromProperties("headlessMode").trim();

                if (headlessMode == null || headlessMode.equals(""))
                    throw new IOException("Key-value pair is not set, check 'appConfig.properties' file");
                else if (headlessMode.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--start-maximized");
                }

                if (System.getProperty("os.name").toLowerCase().contains("windows"))
                    System.setProperty("webdriver.chrome.driver", DEFAULT_DRIVER_PATH + "chromedriver.exe");
                else
                    System.setProperty("webdriver.chrome.driver", DEFAULT_DRIVER_PATH + "chromedriver");

                driver = new ChromeDriver(options);
                setElementPageScriptLoadTimeout(driver);

                break;
            case "firefox":
                if (System.getProperty("os.name").toLowerCase().contains("windows"))
                    System.setProperty("webdriver.gecko.driver", DEFAULT_DRIVER_PATH + "geckodriver.exe");
                else
                    System.setProperty("webdriver.gecko.driver", DEFAULT_DRIVER_PATH + "geckodriver");

//                WebDriverManager.chromedriver().setup();
                driver = new FirefoxDriver();
                setElementPageScriptLoadTimeout(driver);

                break;
            default:
                System.out.println("Browser driver is unsupported");
        }

        return driver;
    }


    /**
     * Return the current driver reference in use
     *
     * @return Current driver reference in use
     */
    public WebDriver getDriver() {
        return driver;
    }


    /**
     * Set the current driver reference to null
     */
    public void setDriverToNull() {
        driver = null;
    }


    /**
     * Close the application and all the browser windows loaded
     */
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}