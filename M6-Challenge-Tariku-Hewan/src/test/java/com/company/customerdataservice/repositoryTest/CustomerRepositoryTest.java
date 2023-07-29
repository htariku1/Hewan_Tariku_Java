package com.company.customerdataservice.repositoryTest;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepo;

    @BeforeEach
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void saveCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");
        customer.setAddress1("2222 Art St");
        customer.setAddress2("Apt 10");
        customer.setCity("San Diego");
        customer.setState("CA");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);
        assertThat(customer.getId()).isNotNull();
    }

    @Test
    public void findCustomerById() {
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");
        customer.setAddress1("2222 Art St");
        customer.setAddress2("Apt 10");
        customer.setCity("San Diego");
        customer.setState("CA");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);
        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());

        assertThat(optionalCustomer.isPresent()).isTrue();
        assertThat(optionalCustomer.get().getFirstName()).isEqualTo("Joe");
        assertThat(optionalCustomer.get().getLastName()).isEqualTo("Smith");

    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");
        customer.setAddress1("2222 Art St");
        customer.setAddress2("Apt 10");
        customer.setCity("San Diego");
        customer.setState("CA");
        customer.setPostalCode("12345");
        customer.setCountry("United States");
        customer = customerRepo.save(customer);
        customerRepo.deleteById(customer.getId());

        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
        assertThat(optionalCustomer.isPresent()).isFalse();
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");
        customer.setAddress1("2222 Art St");
        customer.setAddress2("Apt 10");
        customer.setCity("San Diego");
        customer.setState("CA");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);
        customer.setFirstName("Jane");
        customerRepo.save(customer);

        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
        assertThat(optionalCustomer.isPresent()).isTrue();
        assertThat(optionalCustomer.get().getFirstName()).isEqualTo("Jane");
    }
}
