package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class sauceDemo {

    private static WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    public static void launchSauceDemo() {
        getDriver().get(Config_Reader.properties("SauceDemo"));
    }

    public static void Login() {
        getDriver().findElement(By.xpath("//input[@name='user-name']")).sendKeys(Config_Reader.properties("SauceDemoUserName"));
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(Config_Reader.properties("SauceDemoPassword"));
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();
    }

    public static void selectProduct(String product) {
        getDriver().findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        getDriver().findElement(By.xpath("//a[@id='inventory_sidebar_link']")).click();

        if (product.equals("Backpack")) {
            getDriver().findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
        } else if (product.equals("T-Shirt")) {
            getDriver().findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']")).click();
        }
    }

    public static void Checkout() {
        getDriver().findElement(By.xpath("//button[@name='add-to-cart']")).click();
        getDriver().findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        getDriver().findElement(By.xpath("//button[@id='checkout']")).click();
    }

    public static void completeOrder() {
        getDriver().findElement(By.xpath("//input[@id='first-name']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//input[@id='last-name']")).sendKeys("User");
        getDriver().findElement(By.xpath("//input[@id='postal-code']")).sendKeys("55555");
        getDriver().findElement(By.xpath("//input[@id='continue']")).click();
        getDriver().findElement(By.xpath("//button[@id='finish']")).click();
        System.out.println(getDriver().findElement(By.xpath("//h2[text()='Thank you for your order!']")).getText());
        System.out.println(getDriver().findElement(By.xpath("//div[@class='complete-text']")).getText());
    }
}