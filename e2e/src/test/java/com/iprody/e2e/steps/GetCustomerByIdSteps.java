package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.api.CustomerControllerApi;
import org.openapitools.client.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class GetCustomerByIdSteps {

    private final CustomerControllerApi customerControllerApi = new CustomerControllerApi();

    private CustomerDto customerDto;

    private ResponseEntity<CustomerDto> response;

    private int statusCode;

    @When("user want to get customer with existing id {long}")
    public void getRequestWithExistingId(Long id) {
        response = customerControllerApi.getCustomerWithHttpInfo(id);
    }

    @When("user want to get customer with non-existing id {long}")
    public void getRequestWithNonExistingId(Long id) {
        try {
            response = customerControllerApi.getCustomerWithHttpInfo(id);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @Then("response status code for existing id should be {int}")
    public void responseStatusCodeShouldBe201(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("response status code for non-existing id should be {int}")
    public void responseStatusCodeShouldBe404(int statusCode) {
        assertEquals(statusCode, this.statusCode);
    }

    @And("response should contain the customer data with id {long}")
    public void responseShouldContainCreatedCustomerId(Long id) {
        customerDto = response.getBody();
        assertNotNull(customerDto);
        assertEquals(customerDto.getId(), id);
    }

}
