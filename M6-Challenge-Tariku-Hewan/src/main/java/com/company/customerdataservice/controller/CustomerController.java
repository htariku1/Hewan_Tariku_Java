package com.company.customerdataservice.controller;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    //A GET route that returns a specific customer by id
    @GetMapping("/customers/id/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    //A GET route that returns all customers for a specific state
    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {

        return repo.findByState(state);
    }
    //A POST route that creates a new customer
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    //A PUT route that updates an existing customer
    @PutMapping("/customers/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = repo.findById(id);
        if (existingCustomer.isPresent()) {
            customer.setId(id);
            repo.save(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found.");
        }
    }

    //A DELETE route that deletes an existing customer
    @DeleteMapping("/customers/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        Optional<Customer> existingCustomer = repo.findById(id);
        if (existingCustomer.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found.");
        }
    }
}
