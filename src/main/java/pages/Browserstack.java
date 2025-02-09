package pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browserstack {
    public static final String USERNAME = "chrisgayle_uQc1S2";
    public static final String AUTOMATE_KEY = "PerJUmC6A6JEifDhHLQa";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    static WebDriver driver;

    public static WebDriver BrowserstackCapabilities() throws MalformedURLException {

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        capabilities.setCapability("browserName", "Chrome");
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("browserVersion", "120.0");
        bstackOptions.put("userName", "chrisgayle_uQc1S2");
        bstackOptions.put("accessKey", "PerJUmC6A6JEifDhHLQa");
        bstackOptions.put("consoleLogs", "info");
        capabilities.setCapability("bstack:options", bstackOptions);

        URL browserStackUrl = new URL(URL);
        driver = new RemoteWebDriver(browserStackUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;
    }

}
