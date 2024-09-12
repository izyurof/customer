Feature: Get All Customers

  Scenario: Successfully request all customers
    When user want to get all customers
    Then response status code should be 200
    And response body should contain a list of customers
