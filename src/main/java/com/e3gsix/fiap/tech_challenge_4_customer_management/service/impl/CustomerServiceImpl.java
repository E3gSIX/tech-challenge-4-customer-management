package com.e3gsix.fiap.tech_challenge_4_customer_management.service.impl;

import com.e3gsix.fiap.tech_challenge_4_customer_management.model.Customer;
import com.e3gsix.fiap.tech_challenge_4_customer_management.repository.CustomerRepository;
import com.e3gsix.fiap.tech_challenge_4_customer_management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<?> findById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }

    @Override
    public ResponseEntity<?> create(Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @Override
    public ResponseEntity<?> update(Long customerId, Customer customer) {
        Customer customerExist = customerRepository.findById(customerId).orElse(null);

        if (customerExist != null) {
            customerExist.setName(customer.getName());
            customerExist.setUf(customer.getUf());
            customerExist.setAddress(customer.getAddress());
            return ResponseEntity.ok(customerRepository.save(customerExist));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long customerId) {
        Customer customerExist = customerRepository.findById(customerId).orElse(null);

        if (customerExist != null) {
            customerRepository.delete(customerExist);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }
}