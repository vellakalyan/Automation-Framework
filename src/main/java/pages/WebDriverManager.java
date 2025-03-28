package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverManager() {}

    public static WebDriver getDriver() {
        if (driver.get() == null || ((RemoteWebDriver) driver.get()).getSessionId() == null) {
            try {
                driver.set(createBrowserstackDriver());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    private static WebDriver createBrowserstackDriver() throws MalformedURLException {
        String USERNAME = "chrisgayle_uQc1S2";
        String AUTOMATE_KEY = "PerJUmC6A6JEifDhHLQa";
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<>();
        capabilities.setCapability("browserName", "Chrome");
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("browserVersion", "120.0");
        bstackOptions.put("userName", USERNAME);
        bstackOptions.put("accessKey", AUTOMATE_KEY);
        capabilities.setCapability("bstack:options", bstackOptions);

        URL browserStackUrl = new URL(URL);
        WebDriver newDriver = new RemoteWebDriver(browserStackUrl, capabilities);
        newDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return newDriver;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Ensure thread-safe cleanup
        }
    }
}
