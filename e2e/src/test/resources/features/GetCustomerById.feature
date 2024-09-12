Feature: Get Customer by ID

  Scenario: Successful request to get a customer by ID
    When user want to get customer with existing id 1
    Then response status code for existing id should be 200
    And response should contain the customer data with id 1

  Scenario: Fail request to get a customer by ID
    When user want to get customer with non-existing id 999
    Then response status code for non-existing id should be 404
