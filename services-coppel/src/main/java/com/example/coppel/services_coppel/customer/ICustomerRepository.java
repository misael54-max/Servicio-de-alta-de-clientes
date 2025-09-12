package com.example.coppel.services_coppel.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coppel.services_coppel.customer.model.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> { }