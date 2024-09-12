package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.openapitools.client.api.CustomerControllerApi;
import org.openapitools.client.model.CustomerDto;
import org.springframework.http.ResponseEntity;

public class GetAllCustomersSteps {

    private final CustomerControllerApi customerControllerApi = new CustomerControllerApi();

    private ResponseEntity<List<CustomerDto>> response;

    @When("user want to get all customers")
    public void get_request_to_get_all_customers() {
        response = customerControllerApi
                .getAllCustomersWithHttpInfo(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
    }

    @Then("response status code should be {int}")
    public void theResponseStatusShouldBe200(int statusCode) {
        System.out.println(statusCode);
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @And("response body should contain a list of customers")
    public void responseShouldContainListOfCustomers() {
        List<CustomerDto> listOfCustomers = response.getBody();
        assertNotNull(listOfCustomers);
        assertFalse(listOfCustomers.isEmpty());
    }

}
