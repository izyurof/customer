package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
