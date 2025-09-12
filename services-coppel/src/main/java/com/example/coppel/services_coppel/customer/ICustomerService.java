package com.example.coppel.services_coppel.customer;

import com.example.coppel.services_coppel.customer.model.CustomerEntity;
import java.util.*;

public interface ICustomerService {
    List<CustomerEntity> getAll();
    void deleteById(Long id);
    CustomerEntity create(CustomerEntity customer);
    CustomerEntity update(Long id, CustomerEntity customer);
}
