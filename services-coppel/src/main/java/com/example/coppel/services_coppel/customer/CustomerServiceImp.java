package com.example.coppel.services_coppel.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.coppel.services_coppel.customer.model.CustomerEntity;

import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements ICustomerService {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerServiceImp.class);
    private final ICustomerRepository customerRepository;
    
    @Override
    public List<CustomerEntity> getAll() {
        log.info("Fetching all users from the database");
        return customerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting customer with id {}", id);
        if (!customerRepository.existsById(id)) {
            log.error("Customer with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        try {
            log.info("Creating customer with email {}", customer.getEmail());
            customer.setId(null);
            return customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Error creating customer: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating customer: " + e.getMessage());
        }
    }

    @Override
    public CustomerEntity update(Long id, CustomerEntity customer) {
        log.info("Updating customer {}", id);
        CustomerEntity updateCustomer = customerRepository.findById(id).orElse(null);
        if (updateCustomer == null) {
            log.error("Customer with id {} not found. Couldn't update the row.", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id);
        }
        updateCustomer.setName(customer.getName());
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setBirthDate(customer.getBirthDate());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPhoneNumber(customer.getPhoneNumber());
        updateCustomer.setCountry(customer.getCountry());

        return customerRepository.save(updateCustomer);
    }

}
