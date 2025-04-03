package stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Pa;
import org.testng.annotations.AfterClass;
import pages.ParaBank;
import pages.WebDriverManager;
import pages.sauceDemo;
import com.cucumber.listener.Reporter;

import java.net.MalformedURLException;

public class webAutomation {

    @Given("User navigates to the website Saucelabs")
    public void launchSauceDemoWebsite() throws Exception {
        sauceDemo.launchSauceDemo();
    }

    @And("User Logins using (.*) and (.*)")
    public void login(String Username, String Password) throws Exception {
        sauceDemo.Login();

    }

    @And("User navigates to all products page and select (.*)")
    public void productsPage(String product) throws Exception {
        sauceDemo.selectProduct(product);

    }

    @And("^User is able to add (.*) to cart and navigates to checkout page$")
    public void checkout(String product) throws Exception {
        sauceDemo.Checkout();
    }

    @Then("^User fills information in checkout page and completes order")
    public void completeOrder() throws Exception {
        sauceDemo.completeOrder();
    }

    @Given("^User navigates to the website ParaBank$")
    public void userNavigatesToTheWebsiteParaBank() {
        ParaBank.launchParaBank();
    }

    @Then("^User Login using (.*) and (.*)$")
    public void userLoginUsingUsernameAndPassword(String username, String password) throws MalformedURLException {
        ParaBank.paraBanklogin(username, password);
    }

    @When("^User clicks on register option$")
    public void userWantsToRegisterAnAccount() {
        ParaBank.paraBankRegister();
    }

    @Then("^User fills registration form with valid (.*), (.*), (.*) and (.*)$")
    public void userFillsRegistrationFormWithValidInformation(String firstName, String lastName, String userName, String password) {
        ParaBank.paraBankFillRegistrationForm(firstName, lastName, userName, password);
    }

    @Then("^Verify (.*) is logged successfully$")
    public void verifyUserIsLoggedSuccessfully(String userName) {
        ParaBank.VerifyLogin(userName);
    }

    @When("^User wants to view the account overview$")
    public void userWantsToViewTheAccountOverview() {
        ParaBank.accountOverview();
    }

    @Then("^Display the account details of the user$")
    public void displayTheAccountDetailsOfTheUser() throws InterruptedException {
        ParaBank.displayTheAccountDetailsOfTheUser();
    }

    @When("^User wants to transfer (.*) to another account$")
    public void userWantsToTransferFundsToAnotherAccount(String fundsToTransfer) {
        ParaBank.transferFundsToAnotherAccount(fundsToTransfer);
    }

    @Then("^Verify funds are transferred successfully$")
    public void verifyFundsAreTransferredSuccessfully() {
        ParaBank.verifyFundsTransfer();
    }

    @When("^User wants to apply for a loan$")
    public void userWantsToApplyForALoan() {
        ParaBank.applyLoan();
    }

    @Then("^Verify loan is approved for the user$")
    public void verifyLoanIsApprovedForTheUser() {
        ParaBank.verifyLoanApproved();
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }

}
