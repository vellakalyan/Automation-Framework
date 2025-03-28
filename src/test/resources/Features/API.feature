Feature:API Automation for few basic examples

  Scenario Outline: User checks the gender of a person based on their name
    Given User wants to check the gender of a person based on their <Name>
    And Verify status code of the Genderize API is 200
    Then Display the gender of a person <Name>

    Examples:
      | Name    |
      | Dhoni   |
      | Kohli   |
      | Anushka |
      | Prabhas |
      | Rosy    |

  Scenario Outline: User retrieves information for a ZIP code
    Given User wants to get information for ZIP code <ZipCode>
    When User sends a GET request to the Zippopotam API for the ZIP code
    Then Verify the status code of the Zippopotam API is 200
    And Display the information for ZIP code <ZipCode>

    Examples:
      | ZipCode |
      | 33162   |
      | 90210   |
      | 02115   |


  Scenario Outline: User checks the predicted age of a person based on their Name
    Given User wants to check the predicted age of a person based on their <Name>
    Then Verify the status code of the Agify API is 200
    And Display the predicted age for person <Name>

    Examples:
      | Name    |
      | dhoni   |
      | dohli   |
      | anushka |
      | prabhas |
      | rosy    |


  Scenario: User checks the current price of Bitcoin
    Given User wants to check the current BitCoin price using the API
    And Verify status code of the BitCoin Price API is 200
    Then Display the current price of Bitcoin in USD