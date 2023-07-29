package com.company.customerdataservice.controllerTest;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        // Initialize customer1
        customer1.setId(1);
        customer2 = new Customer();
        // Initialize customer2
        customer2.setId(2);
    }

    @Test
    public void getCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(customer1, customer2);
        given(customerRepository.findAll()).willReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions here to verify the returned JSON
    }

    @Test
    public void getCustomerById() throws Exception {
        given(customerRepository.findById(1)).willReturn(Optional.of(customer1));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions here to verify the returned JSON
    }

    @Test
    public void getCustomersByState() throws Exception {
        List<Customer> customers = Arrays.asList(customer1);
        given(customerRepository.findByState("NY")).willReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/state/{state}", "NY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions here to verify the returned JSON
    }

    @Test
    public void addCustomer() throws Exception {
        customer1.setId(1);
        customer1.setFirstName("Joe");
        customer1.setLastName("Smith");
        customer1.setEmail("joe.smith@gmail.com");
        customer1.setCompany("BigCo");
        customer1.setPhone("111-222-3456");
        customer1.setAddress1("2222 Art St");
        customer1.setAddress2("Apt 10");
        customer1.setCity("San Diego");
        customer1.setState("CA");
        customer1.setPostalCode("12345");
        customer1.setCountry("United States");

        given(customerRepository.save(customer1)).willReturn(customer1);

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer1);

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
        // Add more assertions here to verify the returned JSON
    }

    @Test
    public void updateCustomer() throws Exception {
        customer1.setId(1);
        customer1.setFirstName("Joe");
        customer1.setLastName("Smith");
        customer1.setEmail("joe.smith@gmail.com");
        customer1.setCompany("BigCo");
        customer1.setPhone("111-222-3456");
        customer1.setAddress1("2222 Art St");
        customer1.setAddress2("Apt 10");
        customer1.setCity("San Diego");
        customer1.setState("CA");
        customer1.setPostalCode("12345");
        customer1.setCountry("United States");

        given(customerRepository.findById(1)).willReturn(Optional.of(customer1));

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer1);

        mockMvc.perform(MockMvcRequestBuilders.put("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteCustomer() throws Exception {
        given(customerRepository.findById(1)).willReturn(Optional.of(customer1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCustomerById_notFound() throws Exception {
        given(customerRepository.findById(1)).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomer_notFound() throws Exception {
        customer1.setId(1);
        given(customerRepository.findById(1)).willReturn(Optional.empty());

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer1);

        mockMvc.perform(MockMvcRequestBuilders.put("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomer_notFound() throws Exception {
        given(customerRepository.findById(1)).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
