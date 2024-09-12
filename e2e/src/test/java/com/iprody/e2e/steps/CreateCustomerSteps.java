package com.iprody.e2e.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import org.openapitools.client.api.CustomerControllerApi;
import org.openapitools.client.model.ContactDetailsDto;
import org.openapitools.client.model.CountryDto;
import org.openapitools.client.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class CreateCustomerSteps {

    private final CustomerControllerApi customerControllerApi = new CustomerControllerApi();

    private CustomerDto customerDto;

    private ContactDetailsDto contactDetailsDto;

    private CountryDto countryDto;

    private ResponseEntity<CustomerDto> response;

    private int statusCode;

    @Given("customer with valid data:")
    public void createCustomerWithValidData(Map<String, String> customerData) {
        customerDto = new CustomerDto();
        customerDto.setName(customerData.get("name"));
        customerDto.setSurname(customerData.get("surname"));
        contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setEmail(customerData.get("contactDetails.email"));
        contactDetailsDto.setTelegramId(customerData.get("contactDetails.telegramId"));
        customerDto.setContactDetailsDto(contactDetailsDto);
        countryDto = new CountryDto();
        countryDto.setId(Long.parseLong(customerData.get("country.id")));
        countryDto.setCountryCode(customerData.get("country.code"));
        countryDto.setName(customerData.get("country.name"));
        customerDto.setCountryDto(countryDto);
    }

    @Given("customer with invalid data:")
    public void createCustomerWithInvalidData(Map<String, String> customerData) {
        customerDto = new CustomerDto();
        customerDto.setName(customerData.get("name"));
        customerDto.setSurname(customerData.get("surname"));
    }

    @When("user want to create a new customer with valid data")
    public void postRequestToCreateCustomerWithValidData() {
        response = customerControllerApi.createCustomerWithHttpInfo(customerDto);
    }

    @When("user want to create a new customer with invalid data")
    public void postRequestToCreateCustomerWithInvalidData() {
        try {
            response = customerControllerApi.createCustomerWithHttpInfo(customerDto);
        } catch (HttpClientErrorException e) {
            statusCode = e.getStatusCode().value();
        }
    }

    @Then("response status code for valid customer should be {int}")
    public void responseStatusCodeShouldBe201(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("response status code for invalid customer should be {int}")
    public void responseStatusCodeShouldBe400(int statusCode) {
        assertEquals(statusCode, this.statusCode);
    }

    @And("response body should contain the created customer data")
    public void responseBodyShouldContainTheCreatedCustomerData() {
        CustomerDto responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(customerDto.getName(), responseBody.getName());
        assertEquals(customerDto.getSurname(), responseBody.getSurname());
        assertEquals(customerDto.getCountryDto().getId(), responseBody.getCountryDto().getId());
        assertEquals(customerDto.getCountryDto().getName(), responseBody.getCountryDto().getName());
        assertEquals(
                customerDto.getCountryDto().getCountryCode(),
                responseBody.getCountryDto().getCountryCode()
        );
        assertEquals(
                customerDto.getContactDetailsDto().getEmail(),
                responseBody.getContactDetailsDto().getEmail()
        );
        assertEquals(
                customerDto.getContactDetailsDto().getTelegramId(),
                responseBody.getContactDetailsDto().getTelegramId()
        );
    }
}
