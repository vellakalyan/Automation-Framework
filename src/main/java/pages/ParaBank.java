package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBank {

    private static WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    public static void launchParaBank() {
        getDriver().get(Config_Reader.properties("ParaBankURL"));
    }

    public static void paraBanklogin(String username, String password) {
        getDriver().manage().window().maximize();
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@value='Log In']")).click();
    }

    public static void paraBankRegister() {
        getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/p[2]/a")).click();

    }

    public static void paraBankFillRegistrationForm(String firstName, String lastName, String userName, String password) {

        getDriver().findElement(By.xpath("//input[@id=\"customer.firstName\"]")).sendKeys(firstName);
        getDriver().findElement(By.xpath("//input[@id=\"customer.lastName\"]")).sendKeys(lastName);
        getDriver().findElement(By.xpath("//input[@id=\"customer.address.street\"]")).sendKeys("3828 Piermont Dr");
        getDriver().findElement(By.xpath("//input[@id=\"customer.address.city\"]")).sendKeys("Albuquerque");
        getDriver().findElement(By.xpath("//input[@id=\"customer.address.state\"]")).sendKeys("NM");
        getDriver().findElement(By.xpath("//input[@id=\"customer.address.zipCode\"]")).sendKeys("91764");
        getDriver().findElement(By.xpath("//input[@id=\"customer.phoneNumber\"]")).sendKeys("987654321");
        getDriver().findElement(By.xpath("//input[@id=\"customer.ssn\"]")).sendKeys("555-50-1234");

        getDriver().findElement(By.xpath("//input[@id=\"customer.username\"]")).sendKeys(userName);
        getDriver().findElement(By.xpath("//input[@id=\"customer.password\"]")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@id=\"repeatedPassword\"]")).sendKeys(password);

        getDriver().findElement(By.xpath("//input[@type=\"submit\"]")).click();
    }

    public static void VerifyLogin(String userName) {

        System.out.println(getDriver().findElement(By.xpath("//p[@class='smallText']")).getText());
        if (getDriver().findElement(By.xpath("//p[@class='smallText']")).getText().equalsIgnoreCase("Welcome " + userName)) {
            System.out.println("User is logged successfully");
        }
    }

    public static void accountOverview() {

        getDriver().findElement(By.xpath("//a[text()='home']")).click();
        getDriver().findElement(By.xpath("//a[text()='Accounts Overview']")).click();
        System.out.println("User is on " + getDriver().getTitle() + " Page");

    }

    public static void displayTheAccountDetailsOfTheUser() throws InterruptedException {

        getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/table/tbody/tr[5]/td[1]")).getText();
        System.out.println(getDriver().findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/table/tbody/tr[12]/td[2]/b")).getText());
    }

    public static void transferFundsToAnotherAccount(String fundsToTransfer) {

        getDriver().findElement(By.xpath("//a[text()='home']")).click();
        getDriver().findElement(By.xpath("//a[text()='Transfer Funds']")).click();

        getDriver().findElement(By.xpath("//input[@id='amount']")).sendKeys(fundsToTransfer);
        getDriver().findElement(By.xpath("//form[@id='transferForm']")).click();
        getDriver().findElement(By.xpath("//input[@value='Transfer']")).click();
    }

    public static void verifyFundsTransfer() {
        if (getDriver().findElement(By.xpath("//h1[text()='Transfer Complete!']")).getText().equalsIgnoreCase("Transfer Complete!")) {

            System.out.println("Funds are transferred successfully");
        }
    }

    public static void applyLoan() {

        getDriver().findElement(By.xpath("//a[text()='home']")).click();
        getDriver().findElement(By.xpath("//a[text()='Request Loan']")).click();
        getDriver().findElement(By.xpath("//input[@id='amount']")).sendKeys("10");
        getDriver().findElement(By.xpath("//input[@id='downPayment']")).sendKeys("1");
        getDriver().findElement(By.xpath("//input[@value='Apply Now']")).click();
    }

    public static void verifyLoanApproved() {

        getDriver().findElement(By.xpath("//a[text()='home']")).click();
    }
}
