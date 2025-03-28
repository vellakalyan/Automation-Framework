Feature:Online Order Placement

  @OrderPlacement
  Scenario Outline: Online order Placement
    Given User navigates to the website Saucelabs
    Then User Login using Username and Password
    And User navigates to all products page and select <Product>
    And User is able to add <Product> to cart and navigates to checkout page
    Then User fills information in checkout page and completes order
    Examples:
      | Product  |
      | Backpack |
#      | T-Shirt  |