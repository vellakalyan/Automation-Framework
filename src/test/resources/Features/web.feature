Feature:BackPack_Online_Order

  @Registration
  Scenario Outline: ParaBank Account Registration
    Given User navigates to the website ParaBank
    When User clicks on register option
    Then User fills registration form with valid <FirstName>, <LastName>, <Username> and <Password>
    Examples:
      | FirstName | LastName | Username | Password |
      | Jon       | Snow     | JonSnow  | Jon123   |
      | Walter    | White    | WhiteW   | WW123    |


  @Login
  Scenario Outline: ParaBank User Login
    Given User navigates to the website ParaBank
    And User Login using <Username> and <Password>
    Then Verify <Username> is logged successfully
    Examples:
      | Username      | Password        |
      | daniel walker | danielwalker123 |

  @AccountOverview
  Scenario Outline: ParaBank User Account overview
    Given User navigates to the website ParaBank
    And User Login using <Username> and <Password>
    When User wants to view the account overview
    Then Display the account details of the user
    Examples:
      | Username      | Password        |
      | daniel walker | danielwalker123 |

  @TransferFunds
  Scenario Outline: ParaBank Account Transfer funds
    Given User navigates to the website ParaBank
    And User Login using <Username> and <Password>
    When User wants to transfer <funds> to another account
    Then Verify funds are transferred successfully
    Examples:
      | Username      | Password        | funds |
      | daniel walker | danielwalker123 | 10    |


  @ApplyLoan
  Scenario Outline: ParaBank Account Loan Request
    Given User navigates to the website ParaBank
    And User Login using <Username> and <Password>
    When User wants to apply for a loan
    Then Verify loan is approved for the user
    Examples:
      | Username      | Password        |
      | daniel walker | danielwalker123 |