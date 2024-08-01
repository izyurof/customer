package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Cucumber configuration.
 */

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoadApplicationSteps {

    private boolean test = false;

    @Given("the cucumber test is successfully")
    public void given() {

    }

    @When("I run the test")
    public void when() {
        test = true;
    }

    @Then("I should see the test pass")
    public void then() {
        assertTrue(test);
    }

}
