Feature:Validating API Methods - Post, Get, Put, Patch, Delete

  Scenario Outline: User retrieves details of an existing booking
    Given User wants to retrieve the details of a booking with ID <bookingId>
    When User sends a GET request to the herokuBooking endpoint
    Then Verify the status code of the Get Booking API is 200
    And Verify the booking details are retrieved successfully

    Examples:
      | bookingId |
      | 1         |
      | 5         |
      | 10        |

  Scenario Outline: User create a hotel booking
    Given User wants to create a booking with <firstname>, <lastname>, <totalPrice>, <depositPaid>, <checkin>, <checkout>, and <additionalNeeds>
    When User wants to book hotel using herokuBooking endpoint
    Then Verify the status code of the Create Booking API is 200
    And Verify the booking is created successfully with the provided details

    Examples:
      | firstname | lastname | totalPrice | depositPaid | checkin    | checkout   | additionalNeeds |
      | Jon       | SNOW     | 12345      | true        | 2025-01-30 | 2025-01-31 | Lunch           |
      | Jane      | Doe      | 500        | false       | 2025-02-15 | 2025-02-20 | Dinner          |
      | Peter     | Pan      | 2000       | true        | 2025-03-01 | 2025-03-05 | Breakfast       |


  Scenario Outline: User updates an existing hotel booking
    Given User has an existing booking with ID <bookingId>
    And User wants to update the booking with <firstname>, <lastname>, <totalPrice>, <depositPaid>, <checkin>, <checkout>, and <additionalNeeds>
    When User wants to update hotel booking using herokuBookingId endpoint
    Then Verify the status code of the Update Booking API is 200
    And Verify the booking with ID <bookingId> is updated successfully with the provided details

    Examples:
      | bookingId | firstname | lastname | totalPrice | depositPaid | checkin    | checkout   | additionalNeeds |
      | 1         | James     | Brown    | 111        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
      | 5         | Sally     | Smith    | 789        | false       | 2024-05-10 | 2024-05-15 | Dinner          |
      | 10        | Robert    | Jones    | 1500       | true        | 2025-04-01 | 2025-04-07 | Lunch           |

  Scenario Outline: User deletes an existing hotel booking
    Given User has an existing booking with ID <bookingId>
    When User wants to delete hotel booking using herokuBookingId endpoint
    Then Verify the status code of the Delete Booking API is 201

    Examples:
      | bookingId |
      | 25        |
      | 10        |
      | 30        |

  Scenario Outline: User partially updates an existing hotel booking
    Given User has an existing booking with ID <bookingId>
    And User wants to partially update the booking with firstname "<firstname>" and lastname "<lastname>"
    When User wants to partially update hotel booking using herokuBookingId endpoint
    Then Verify the status code of the Update Booking API is 200
    And Verify the booking with ID <bookingId> is partially updated successfully with the provided firstname and lastname

    Examples:
      | bookingId | firstname | lastname |
      | 1         | James     | Brown    |
      | 5         | Sally     | Smith    |
      | 10        | Robert    | Jones    |


