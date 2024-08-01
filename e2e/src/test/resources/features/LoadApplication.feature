Feature: Cucumber init test
  Scenario: Verify cucumber is work
    Given the cucumber test is successfully
    When I run the test
    Then I should see the test pass
