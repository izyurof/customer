package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.api.CustomerControllerApi;
import org.openapitools.client.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class UpdateCustomerSteps {

    private final CustomerControllerApi customerControllerApi = new CustomerControllerApi();

    private CustomerDto customerDto;

    private ResponseEntity<Void> response;

    private int statusCode;

    @Given("exist customer with ID {long}")
    public void requestToGetCustomerById(Long id) {
        customerDto = customerControllerApi.getCustomerWithHttpInfo(id).getBody();
    }

    @When("user want to update valid name for customer with ID {long}")
    public void patchRequestToUpdateValidName(Long id) {
        customerDto.setName("ValidName");
        response = customerControllerApi.updateCustomerNameWithHttpInfo(id, customerDto);
    }

    @When("user want to update invalid name for customer with ID {long}")
    public void patchRequestToUpdateInvalidName(Long id) {
        customerDto.setName("invalid_name_that_is_too_long_and_should_fail_validation");
        try {
            response = customerControllerApi.updateCustomerNameWithHttpInfo(id, customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @When("user want to update valid surname for customer with ID {long}")
    public void patchRequestToUpdateValidSurname(Long id) {
        customerDto.setSurname("ValidSurname");
        response = customerControllerApi.updateCustomerSurnameWithHttpInfo(id, customerDto);
    }


    @When("user want to update invalid surname for customer with ID {long}")
    public void patchRequestToUpdateInvalidSurname(Long id) {
        customerDto.setSurname("invalid_surname_that_is_too_long_and_should_fail_validation");
        try {
            response = customerControllerApi.updateCustomerSurnameWithHttpInfo(id, customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @When("user want to update valid email for customer with ID {long}")
    public void patchRequestToUpdateValidEmail(Long id) {
        customerDto.getContactDetailsDto().setEmail("new_email@example.com");
        response = customerControllerApi.updateCustomerEmailWithHttpInfo(id, customerDto);
    }

    @When("user want to update invalid email for customer with ID {long}")
    public void patchRequestToUpdateInvalidEmail(Long id) {
        customerDto.getContactDetailsDto().setEmail("invalid_email");
        try {
            response = customerControllerApi.updateCustomerEmailWithHttpInfo(id, customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @When("user want to update valid telegramId for customer with ID {long}")
    public void patchRequestToUpdateValidTelegramId(Long id) {
        customerDto.getContactDetailsDto().setTelegramId("@new_telegram_id");
        response = customerControllerApi.updateCustomerTelegramIdWithHttpInfo(id, customerDto);
    }

    @When("user want to update invalid telegramId for customer with ID {long}")
    public void patchRequestToUpdateInvalidTelegramId(Long id) {
        customerDto.getContactDetailsDto().setTelegramId("@l");
        try {
            response = customerControllerApi.updateCustomerTelegramIdWithHttpInfo(id, customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @When("user want to update valid country for customer with ID {long}")
    public void patchRequestToUpdateValidCountry(Long id) {
        customerDto.getCountryDto().setId(3L);
        customerDto.getCountryDto().setCountryCode("AGO");
        customerDto.getCountryDto().setName("Angola");
        response = customerControllerApi.updateCustomerCountryWithHttpInfo(id, customerDto);
    }

    @When("user want to update invalid country for customer with ID {long}")
    public void patchRequestToUpdateInvalidCountry(Long id) {
        customerDto.setCountryDto(null);
        try {
            response = customerControllerApi.updateCustomerCountryWithHttpInfo(id, customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @Then("response status code for valid data should be {int}")
    public void responseStatusForValidDataShouldBe204(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("response status code for invalid data should be {int}")
    public void responseStatusCodeForInvalidDataShouldBe400(int statusCode) {
        assertEquals(statusCode, this.statusCode);
    }
}
