package com.iprody.customerservice.controllers;

import static com.iprody.customerservice.utils.builder.ContactDetailsDtoBuilder.getContactDetailsDtoWithId;
import static com.iprody.customerservice.utils.builder.CountryDtoBuilder.getCountryDtoWithId;
import static com.iprody.customerservice.utils.builder.CustomerDtoBuilder.getCustomerDtoWithId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iprody.customerservice.dto.customer.CustomerDto;
import com.iprody.customerservice.handlers.ExceptionsHandler;
import com.iprody.customerservice.services.CustomerService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new ExceptionsHandler())
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void shouldReturnStatus201ThenValidCustomer() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        when(customerService.save(any(CustomerDto.class))).thenReturn(customerDto);

        // when
        ResultActions perform = mockMvc.perform(post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)));
        // then
        perform.andExpect(status().isCreated());
        validateCustomerJsonResponse(perform, List.of(customerDto));
        verify(customerService, times(1))
                .save(any(CustomerDto.class));
    }

    private static CustomerDto getFullFieldCustomerDto(Long id) {
        CustomerDto customerDto = getCustomerDtoWithId(id);
        customerDto.setCountryDto(getCountryDtoWithId(id));
        customerDto.setContactDetailsDto(getContactDetailsDtoWithId(id));
        return customerDto;
    }

    @Test
    public void shouldReturnStatus400ThenInvalidCustomer() throws Exception {
        // given
        CustomerDto customerDto = new CustomerDto();

        // when
        mockMvc.perform(post("/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                // then
                .andExpect(status().isBadRequest());
        verify(customerService, times(0))
                .save(any(CustomerDto.class));
    }

    @Test
    public void shouldReturnStatus200ThenCustomerIsExist() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        when(customerService.findById(customerId)).thenReturn(Optional.of(customerDto));

        // when
        ResultActions perform = mockMvc.perform(get("/v1/customers/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)));
        // then
        perform.andExpect(status().isOk());
        validateCustomerJsonResponse(perform, List.of(customerDto));
        verify(customerService, times(1)).findById(customerId);
    }

    @Test
    public void shouldReturnStatus404ThenCustomerIsNotExist() throws Exception {
        // given
        Long customerId = 1L;
        when(customerService.findById(customerId)).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/v1/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).findById(customerId);
    }

    @Test
    public void shouldReturnStatus200WhenGetAllCustomers() throws Exception {
        // given
        CustomerDto firstCustomerDto = getFullFieldCustomerDto(1L);
        CustomerDto secondCustomerDto = getFullFieldCustomerDto(2L);
        List<CustomerDto> customers = List.of(firstCustomerDto, secondCustomerDto);
        Page<CustomerDto> pageOfCustomers = new PageImpl<>(customers);
        when(customerService.findAll(any(), any(), any(), any(), any()))
                .thenReturn(pageOfCustomers);

        // when
        ResultActions perform = mockMvc.perform(get("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pageOfCustomers.getContent())));

        // then
        perform.andExpect(status().isOk());
        validateCustomerJsonResponse(perform, customers);
        verify(customerService, times(1))
                .findAll(any(), any(), any(), any(), any());
    }

    @Test
    public void shouldReturnStatus200WhenUpdateCustomerName() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        doNothing().when(customerService).updateCustomerName(anyLong(), any(CustomerDto.class));

        // when-then
        mockMvc.perform(patch("/v1/customers/{id}/update/name", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                // then
                .andExpect(status().isNoContent());
        verify(customerService, times(1))
                .updateCustomerName(customerId, customerDto);
    }

    @Test
    public void shouldReturnStatus200WhenUpdateCustomerSurname() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        doNothing().when(customerService).updateCustomerSurname(anyLong(), any(CustomerDto.class));

        // when
        mockMvc.perform(patch("/v1/customers/{id}/update/surname", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(customerDto)))
                // then
                .andExpect(status().isNoContent());
        verify(customerService, times(1))
                .updateCustomerSurname(customerId, customerDto);
    }

    @Test
    public void shouldReturnStatus200WhenUpdateCustomerEmail() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        doNothing().when(customerService).updateCustomerEmail(anyLong(), any(CustomerDto.class));
        
        // when
        mockMvc.perform(patch("/v1/customers/{id}/update/email", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                // then
                .andExpect(status().isNoContent());
        verify(customerService, times(1))
                .updateCustomerEmail(customerId, customerDto);
    }

    @Test
    public void shouldReturnStatus200WhenUpdateCustomerTelegramId() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        doNothing()
                .when(customerService)
                .updateCustomerTelegramId(anyLong(), any(CustomerDto.class));

        // when
        mockMvc.perform(patch("/v1/customers/{id}/update/telegram_id", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                // then
                .andExpect(status().isNoContent());
        verify(customerService, times(1))
                .updateCustomerTelegramId(customerId, customerDto);
    }

    @Test
    public void shouldReturnStatus200WhenUpdateCustomerCountry() throws Exception {
        // given
        CustomerDto customerDto = getFullFieldCustomerDto(1L);
        Long customerId = customerDto.getId();
        doNothing()
                .when(customerService)
                .updateCustomerCountry(anyLong(), any(CustomerDto.class));

        // when
        mockMvc.perform(patch("/v1/customers/{id}/update/country", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                // then
                .andExpect(status().isNoContent());
        verify(customerService, times(1))
                .updateCustomerCountry(customerId, customerDto);
    }
    
    private void validateCustomerJsonResponse(
            ResultActions result,
            List<CustomerDto> customers
    ) throws Exception {
        for (int i = 0; i < customers.size(); i++) {
            CustomerDto customerDto = customers.get(i);
            String numberOfJsonElement = customers.size() > 1 ? "$[" + i + "]." : "$.";

            result.andExpect(jsonPath(numberOfJsonElement + "id")
                            .value(customerDto.getId()))
                    .andExpect(jsonPath(numberOfJsonElement + "name")
                            .value(customerDto.getName()))
                    .andExpect(jsonPath(numberOfJsonElement + "surname")
                            .value(customerDto.getSurname()))
                    .andExpect(jsonPath(numberOfJsonElement + "contactDetailsDto.id")
                            .value(customerDto.getContactDetailsDto().getId()))
                    .andExpect(jsonPath(numberOfJsonElement + "contactDetailsDto.email")
                            .value(customerDto.getContactDetailsDto().getEmail()))
                    .andExpect(jsonPath(numberOfJsonElement + "contactDetailsDto.telegramId")
                            .value(customerDto.getContactDetailsDto().getTelegramId()))
                    .andExpect(jsonPath(numberOfJsonElement + "countryDto.id")
                            .value(customerDto.getCountryDto().getId()))
                    .andExpect(jsonPath(numberOfJsonElement + "countryDto.name")
                            .value(customerDto.getCountryDto().getName()))
                    .andExpect(jsonPath(numberOfJsonElement + "countryDto.countryCode")
                            .value(customerDto.getCountryDto().getCountryCode()));
        }
    }
}
