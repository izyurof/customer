Feature: Update Customer

  Scenario: Update Customer with valid name
    Given exist customer with ID 1
    When user want to update valid name for customer with ID 1
    Then response status code for valid data should be 204

  Scenario: Update Customer with invalid name
    Given exist customer with ID 1
    When user want to update invalid name for customer with ID 1
    Then response status code for invalid data should be 400

  Scenario: Update Customer with valid surname
    Given exist customer with ID 1
    When user want to update valid surname for customer with ID 1
    Then response status code for valid data should be 204

  Scenario: Update Customer with invalid surname
    Given exist customer with ID 1
    When user want to update invalid surname for customer with ID 1
    Then response status code for invalid data should be 400


  Scenario: Update Customer with valid email
    Given exist customer with ID 1
    When user want to update valid email for customer with ID 1
    Then response status code for valid data should be 204

  Scenario: Update Customer with invalid email
    Given exist customer with ID 1
    When user want to update invalid email for customer with ID 1
    Then response status code for invalid data should be 400

  Scenario: Update Customer with valid telegramId
    Given exist customer with ID 1
    When user want to update valid telegramId for customer with ID 1
    Then response status code for valid data should be 204

  Scenario: Update Customer with invalid telegramId
    Given exist customer with ID 1
    When user want to update invalid telegramId for customer with ID 1
    Then response status code for invalid data should be 400

  Scenario: Update Customer with valid country
    Given exist customer with ID 1
    When user want to update valid country for customer with ID 1
    Then response status code for valid data should be 204

  Scenario: Update Customer with invalid country
    Given exist customer with ID 1
    When user want to update invalid country for customer with ID 1
    Then response status code for invalid data should be 400
