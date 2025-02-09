package stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONObject;
import pages.APIs;
import pages.Config_Reader;

import java.net.MalformedURLException;

public class APIAutomation {
    Response bitcoinPriceResponse, genderizeResponse = null;

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
        System.out.println("url"+Config_Reader.properties("GenderizeAPI")+name);
        genderizeResponse = APIs.restGetCall(Config_Reader.properties("GenderizeAPI"));
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

    @Then("^Display the gender of a person$")
    public void displayTheGenderOfAPerson() {
        JSONObject jsonObject = new JSONObject(genderizeResponse.asString());

        System.out.println(jsonObject.get("gender"));

    }
}
