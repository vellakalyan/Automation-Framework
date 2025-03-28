Feature:API Automation

  Scenario: User checks the current price of Bitcoin
    Given User wants to check the current BitCoin price using the API
    And Verify status code of the BitCoin Price API is 200
    Then Display the current price of Bitcoin in USD

  Scenario Outline: User checks the gender of a person based on their name
    Given User wants to check the gender of a person based on their <name>
    And Verify status code of the Genderize API is 200
#    Then Display the gender of a person

   Examples:
     | name    |
     | Dhoni   |
##     | Kohli   |
##     | Anushka |
##     | Prabhas |
##     | Rosy    |
