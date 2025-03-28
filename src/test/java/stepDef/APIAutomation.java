package stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import pages.APIs;
import pages.Config_Reader;

import java.net.MalformedURLException;
import java.util.Base64;

public class APIAutomation {
    Response bitcoinPriceResponse, genderizeResponse = null;
    private Response response;
    private JSONObject requestBody_Update, requestBody_Patch;
    private JSONObject requestBody;
    private int bookingId;
    private String zipCode;
    private Response zippopotamResponse;
    private Response agifyResponse;

    @Given("^User wants to check the current BitCoin price using the API$")
    public void BitCoinPrice() {

        bitcoinPriceResponse = APIs.restGetCall(Config_Reader.properties("bitcoinPriceAPI"));
        System.out.println("BitcoinPrice API is hit, status code: " + bitcoinPriceResponse.statusCode());

    }

    @Then("^Display the current price of Bitcoin in USD$")
    public void displayTheCurrentPriceOfBitcoinInUSD() {

        JSONObject jsonObject = new JSONObject(bitcoinPriceResponse.asString());
        JSONObject bpi = jsonObject.getJSONObject("bpi");
        JSONObject usd = bpi.getJSONObject("USD");
        String bitcoinPriceInUSD = usd.getString("rate");
        System.out.println("Bitcoin price in USD: " + bitcoinPriceInUSD);

    }

    @And("^Verify status code of the BitCoin Price API is (.*)$")
    public void verifyStatusCodeOfTheBitCoinPriceAPI(int statusCode) throws MalformedURLException {
        if (bitcoinPriceResponse.getStatusCode() == statusCode) {
            System.out.println("Status code matches");
        } else {
            System.out.println("Status code doesn't match");
        }
    }

    @Given("^User wants to check the gender of a person based on their (.*)$")
    public void Genderize(String name) {

        genderizeResponse = APIs.restGetCall(Config_Reader.properties("GenderizeAPI"), "name", name);
        System.out.println("Genderize API is hit, status code: " + genderizeResponse.statusCode());

    }

    @And("^Verify status code of the Genderize API is (\\d+)$")
    public void verifyStatusCodeOfTheGenderizeAPIIs(int statusCode) {
        if (genderizeResponse.getStatusCode() == statusCode) {
            System.out.println("Status code matches");
        } else {
            System.out.println("Status code doesn't match");
        }

    }

    @Then("^Display the gender of a person (.*)$")
    public void displayTheGenderOfAPerson(String name) {
        JSONObject jsonObject = new JSONObject(genderizeResponse.asString());

        String gender = jsonObject.getString("gender");
        System.out.println("The gender of " + name + " is: " + gender);

    }

    @Given("User wants to create a booking with (.*), (.*), (.*), (.*), (.*), (.*), and (.*)")
    public void userWantsToCreateABookingWithTheFollowingDetails(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {

        requestBody = new JSONObject();
        requestBody.put("firstname",firstName );
        requestBody.put("lastname",lastName);
        requestBody.put("totalprice", totalPrice);
        requestBody.put("depositpaid", depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        requestBody.put("bookingdates", bookingDates);

        requestBody.put("additionalneeds", additionalNeeds);
    }

    @When("User wants to book hotel using herokuBooking endpoint")
    public void userSendsAPOSTRequestToTheBookingEndpointWithTheBookingDetails() {
        String url = Config_Reader.properties("herokuappBooking");
        response = APIs.restPostCall(url, requestBody.toString());
        System.out.println("Create Booking API is hit, status code: " + response.statusCode());
    }

    @Then("Verify the status code of the Create Booking API is (.*)")
    public void verifyTheStatusCodeOfTheCreateBookingAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Then("Verify the booking is created successfully with the provided details")
    public void verifyTheBookingIsCreatedSuccessfullyWithTheProvidedDetails() {
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        JSONObject bookingDetails = responseBody.getJSONObject("booking");

        Assert.assertEquals(requestBody.getString("firstname"), bookingDetails.getString("firstname"));
        Assert.assertEquals(requestBody.getString("lastname"), bookingDetails.getString("lastname"));
        Assert.assertEquals(requestBody.getInt("totalprice"), bookingDetails.getInt("totalprice"));
        Assert.assertEquals(requestBody.getBoolean("depositpaid"), bookingDetails.getBoolean("depositpaid"));
        Assert.assertEquals(requestBody.getJSONObject("bookingdates").getString("checkin"), bookingDetails.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(requestBody.getJSONObject("bookingdates").getString("checkout"), bookingDetails.getJSONObject("bookingdates").getString("checkout"));
        Assert.assertEquals(requestBody.getString("additionalneeds"), bookingDetails.getString("additionalneeds"));

        System.out.println("Booking created successfully and details are verified.");
    }


    @Given("User has an existing booking with ID (.*)")
    public void userHasAnExistingBookingWithID(int bookingId) {
        this.bookingId = bookingId;
    }

    @Given("User wants to update the booking with (.*), (.*), (.*), (.*), (.*), (.*), and (.*)")
    public void userWantsToUpdateTheBookingWithSpecificDetails(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {
        requestBody_Update = new JSONObject();
        requestBody_Update.put("firstname", firstName);
        requestBody_Update.put("lastname", lastName);
        requestBody_Update.put("totalprice", totalPrice);
        requestBody_Update.put("depositpaid", depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        requestBody_Update.put("bookingdates", bookingDates);

        requestBody_Update.put("additionalneeds", additionalNeeds);
    }

    @When("User wants to update hotel booking using herokuBookingId endpoint")
    public void userSendsAPUTRequestToTheBookingBookingIdEndpointWithTheUpdatedBookingDetails() {
        String url = Config_Reader.properties("herokuappBooking") + "/" + bookingId;
        response = APIs.restPutCall(url, requestBody_Update.toString(), Config_Reader.properties("HerokuApp_authorization"));
        System.out.println("Update Booking API is hit, status code: " + response.statusCode());
    }

    @Then("Verify the status code of the Update Booking API is (.*)")
    public void verifyTheStatusCodeOfTheUpdateBookingAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Then("Verify the booking with ID (.*) is updated successfully with the provided details")
    public void verifyTheBookingWithIDIsUpdatedSuccessfullyWithTheProvidedDetails(int bookingId) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        Assert.assertEquals(requestBody_Update.getString("firstname"), responseBody.getString("firstname"));
        Assert.assertEquals(requestBody_Update.getString("lastname"), responseBody.getString("lastname"));
        Assert.assertEquals(requestBody_Update.getInt("totalprice"), responseBody.getInt("totalprice"));
        Assert.assertEquals(requestBody_Update.getBoolean("depositpaid"), responseBody.getBoolean("depositpaid"));
        Assert.assertEquals(requestBody_Update.getJSONObject("bookingdates").getString("checkin"), responseBody.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(requestBody_Update.getJSONObject("bookingdates").getString("checkout"), responseBody.getJSONObject("bookingdates").getString("checkout"));
        Assert.assertEquals(requestBody_Update.getString("additionalneeds"), responseBody.getString("additionalneeds"));

        System.out.println("Booking with ID " + bookingId + " updated successfully and details are verified.");
    }

    @When("User wants to delete hotel booking using herokuBookingId endpoint")
    public void userSendsADELETERequestToTheBookingBookingIdEndpoint() {
        String url = Config_Reader.properties("herokuappBooking") + "/" + bookingId;
        response = APIs.restDeleteCall(url, Config_Reader.properties("HerokuApp_authorization"));
        System.out.println("Delete Booking API is hit, status code: " + response.statusCode());
    }

    @Then("Verify the status code of the Delete Booking API is (.*)")
    public void verifyTheStatusCodeOfTheDeleteBookingAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Given("User wants to partially update the booking with firstname \"(.*)\" and lastname \"(.*)\"")
    public void userWantsToPartiallyUpdateTheBookingWithSpecificDetails(String firstName, String lastName) {
        requestBody_Patch = new JSONObject();
        requestBody_Patch.put("firstname", firstName);
        requestBody_Patch.put("lastname", lastName);
    }

    @When("User wants to partially update hotel booking using herokuBookingId endpoint")
    public void userSendsAPATCHRequestToTheBookingBookingIdEndpointWithTheUpdatedBookingDetails() {
        String url = Config_Reader.properties("herokuappBooking") + "/" + bookingId;
        response = APIs.restPatchCall(url, requestBody_Patch.toString(), Config_Reader.properties("HerokuApp_authorization"));
        System.out.println("Update Booking API is hit, status code: " + response.statusCode());
    }


    @Then("Verify the booking with ID (.*) is partially updated successfully with the provided firstname and lastname")
    public void verifyTheBookingWithIDIsPartiallyUpdatedSuccessfullyWithTheProvidedDetails(int bookingId) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        Assert.assertEquals(requestBody_Patch.getString("firstname"), responseBody.getString("firstname"));
        Assert.assertEquals(requestBody_Patch.getString("lastname"), responseBody.getString("lastname"));

        System.out.println("Booking with ID " + bookingId + " partially updated successfully with provided firstname and lastname.");
    }

    @Given("User wants to retrieve the details of a booking with ID (.*)")
    public void userWantsToRetrieveTheDetailsOfABookingWithID(int bookingId) {
        this.bookingId = bookingId;
    }

    @When("User sends a GET request to the herokuBooking endpoint")
    public void userSendsAGETRequestToTheBookingBookingIdEndpoint() {
        String url = Config_Reader.properties("herokuappBooking") + "/" + bookingId;
        response = APIs.restGetCall(url);
        System.out.println("Get Booking API is hit for ID " + bookingId + ", status code: " + response.statusCode());
    }

    @Then("Verify the status code of the Get Booking API is (.*)")
    public void verifyTheStatusCodeOfTheGetBookingAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Then("Verify the booking details are retrieved successfully")
    public void verifyTheBookingDetailsAreRetrievedSuccessfully() {
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        // Add assertions to verify the presence and values of expected fields
        Assert.assertTrue(responseBody.has("firstname"));
        Assert.assertTrue(responseBody.has("lastname"));
        Assert.assertTrue(responseBody.has("totalprice"));
        Assert.assertTrue(responseBody.has("depositpaid"));
        Assert.assertTrue(responseBody.has("bookingdates"));
        Assert.assertTrue(responseBody.getJSONObject("bookingdates").has("checkin"));
        Assert.assertTrue(responseBody.getJSONObject("bookingdates").has("checkout"));
        Assert.assertTrue(responseBody.has("additionalneeds"));

        System.out.println("Booking details retrieved successfully and basic fields are verified.");
    }

    @Given("User wants to get information for ZIP code (.*)")
    public void userWantsToGetInformationForZIPCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @When("User sends a GET request to the Zippopotam API for the ZIP code")
    public void userSendsAGETRequestToTheZippopotamAPIForTheZIPCode() {
        String url = Config_Reader.properties("ZippopotamAPI") + "/us/" + zipCode;
        zippopotamResponse = APIs.restGetCall(url);
        System.out.println("Zippopotam API is hit for ZIP code " + zipCode + ", status code: " + zippopotamResponse.statusCode());
    }

    @Then("Verify the status code of the Zippopotam API is (.*)")
    public void verifyTheStatusCodeOfTheZippopotamAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, zippopotamResponse.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Then("Display the information for ZIP code (.*)")
    public void displayTheInformationForZIPCode(String zipCode) {
        JSONObject jsonObject = new JSONObject(zippopotamResponse.asString());

        System.out.println("Information for ZIP code " + zipCode + ":");
        System.out.println(jsonObject.toString(2)); // Use toString(2) for pretty printing
    }
    @Given("User wants to check the predicted age of a person based on their (.*)")
    public void userWantsToCheckThePredictedAgeOfAPersonBasedOnTheir(String name) {
        agifyResponse = APIs.restGetCall(Config_Reader.properties("AgifyAPI"), "name", name);
    }

    @Then("Verify the status code of the Agify API is (.*)")
    public void verifyTheStatusCodeOfTheAgifyAPIIs(int statusCode) {
        Assert.assertEquals(statusCode, agifyResponse.getStatusCode());
        System.out.println("Status code matches: " + statusCode);
    }

    @Then("Display the predicted age for person (.*)")
    public void displayThePredictedAgeForPerson(String name) {
        JSONObject jsonObject = new JSONObject(agifyResponse.asString());

        if (jsonObject.has("age") && !jsonObject.isNull("age")) {
            int age = jsonObject.getInt("age");
            System.out.println("The predicted age of " + name + " is: " + age);
        } else {
            System.out.println("Could not find predicted age for " + name);
        }
    }
}
