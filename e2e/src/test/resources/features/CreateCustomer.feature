Feature: Create a customer

  Scenario: Successfully create a new customer
    Given customer with valid data:
      | name                      | Jeffrey            |
      | surname                   | Lebowski           |
      | contactDetails.email      | lebowski@gmail.com |
      | contactDetails.telegramId | @lebowski          |
      | country.id                | 1                  |
      | country.name              | Anguilla           |
      | country.code              | AIA                |
    When user want to create a new customer with valid data
    Then response status code for valid customer should be 201
    And response body should contain the created customer data

  Scenario: Fail to create a new customer
    Given customer with invalid data:
      | name    | Jeffrey  |
      | surname | Lebowski |
    When user want to create a new customer with invalid data
    Then response status code for invalid customer should be 400
