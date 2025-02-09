package stepDef.Web;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.net.MalformedURLException;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import pages.Browserstack;
import pages.Config_Reader;

public class webAutomation {
//    WebDriver driver = Browserstack.BrowserstackCapabilities();
WebDriver driver;
    public webAutomation() throws MalformedURLException {
    }

    @Given("^User navigates to the website ParaBank$")
    public void userNavigatesToTheWebsiteParaBank() throws MalformedURLException {
        driver = new ChromeDriver();
        System.out.println("Opening Browser");
        driver.get(Config_Reader.properties("ParaBankURL"));
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
//        Browserstack.BrowserstackCapabilities(Config_Reader.properties("ParaBankURL"));

    }

    @Then("^User Login using (.*) and (.*)$")
    public void userLoginUsingUsernameAndPassword(String username, String password) throws MalformedURLException {

        driver.get(Config_Reader.properties("ParaBankURL"));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

    }

    @And("^User navigates to all products page and select (.*)$")
    public void userNavigatesToAllProductsPageAndSelectProduct() {
        
        
    }

    @Then("^User fills information in checkout page and completes order$")
    public void userFillsInformationInCheckoutPageAndCompletesOrder() {
        
        
    }

    @And("^User is able to add (.*) to cart and navigates to checkout page$")
    public void userIsAbleToAddProductToCartAndNavigatesToCheckoutPage() {


    }

    @When("^User clicks on register option$")
    public void userWantsToRegisterAnAccount() {

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/p[2]/a")).click();
        System.out.println(driver.getCurrentUrl());
    }

    @Then("^User fills registration form with valid (.*), (.*), (.*) and (.*)$")
    public void userFillsRegistrationFormWithValidInformation(String firstName, String lastName, String userName, String password) {
        driver.findElement(By.xpath("//input[@id=\"customer.firstName\"]")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id=\"customer.lastName\"]")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id=\"customer.address.street\"]")).sendKeys("3828 Piermont Dr");
        driver.findElement(By.xpath("//input[@id=\"customer.address.city\"]")).sendKeys("Albuquerque");
        driver.findElement(By.xpath("//input[@id=\"customer.address.state\"]")).sendKeys("NM");
        driver.findElement(By.xpath("//input[@id=\"customer.address.zipCode\"]")).sendKeys("91764");
        driver.findElement(By.xpath("//input[@id=\"customer.phoneNumber\"]")).sendKeys("987654321");
        driver.findElement(By.xpath("//input[@id=\"customer.ssn\"]")).sendKeys("555-50-1234");

        driver.findElement(By.xpath("//input[@id=\"customer.username\"]")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id=\"customer.password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id=\"repeatedPassword\"]")).sendKeys(password);

        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

    }

    @And("^Verify user is registered$")
    public void verifyUserIsRegistered() {


    }

    @Then("^Verify (.*) is logged successfully$")
    public void verifyUserIsLoggedSuccessfully(String userName) {
        System.out.println(driver.findElement(By.xpath("//p[@class='smallText']")).getText());
        if (driver.findElement(By.xpath("//p[@class='smallText']")).getText().equalsIgnoreCase("Welcome "+userName)) {
            System.out.println("User is logged successfully");
        }

    }

    @When("^User wants to view the account overview$")
    public void userWantsToViewTheAccountOverview() {
        driver.findElement(By.xpath("//a[text()='home']")).click();
        driver.findElement(By.xpath("//a[text()='Accounts Overview']")).click();
        System.out.println("User is on "+driver.getTitle()+" Page");
    }

    @Then("^Display the account details of the user$")
    public void displayTheAccountDetailsOfTheUser() throws InterruptedException {
//        driver.wait();

//driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/table/tbody/tr[5]/td[1]")).getText();
        System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/table/tbody/tr[12]/td[2]/b")).getText());

    }

    @When("^User wants to transfer (.*) to another account$")
    public void userWantsToTransferFundsToAnotherAccount(String fundsToTransfer) {
        driver.findElement(By.xpath("//a[text()='home']")).click();
        driver.findElement(By.xpath("//a[text()='Transfer Funds']")).click();

        driver.findElement(By.xpath("//input[@id='amount']")).sendKeys(fundsToTransfer);
        driver.findElement(By.xpath("//form[@id='transferForm']")).click();
        driver.findElement(By.xpath("//input[@value='Transfer']")).click();
    }

    @Then("^Verify funds are transferred successfully$")
    public void verifyFundsAreTransferredSuccessfully() {
      if (driver.findElement(By.xpath("//h1[text()='Transfer Complete!']")).getText().equalsIgnoreCase("Transfer Complete!")) {

          System.out.println("Funds are transferred successfully");
      }
    }

    @When("^User wants to apply for a loan$")
    public void userWantsToApplyForALoan() {
        driver.findElement(By.xpath("//a[text()='home']")).click();
        driver.findElement(By.xpath("//a[text()='Request Loan']")).click();
        driver.findElement(By.xpath("//input[@id='amount']")).sendKeys("10");
        driver.findElement(By.xpath("//input[@id='downPayment']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@value='Apply Now']")).click();


    }

    @Then("^Verify loan is approved for the user$")
    public void verifyLoanIsApprovedForTheUser() {

        driver.findElement(By.xpath("//a[text()='home']")).click();
    }
}
