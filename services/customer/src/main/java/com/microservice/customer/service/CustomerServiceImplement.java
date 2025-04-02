package com.microservice.customer.service;

import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.utility.UtilityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {

    private final CustomerRepository repository;
    private final ValidationService validate;


    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        validate.validate(customerRequest);
        Customer customer = new Customer();
        UtilityModel.toModel(customer, customerRequest);
        Customer savedCustomer = repository.save(customer);
        return UtilityModel.fromModel(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(String id, CustomerRequest customerRequest) {
        validate.validate(customerRequest);
        Customer customer = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        UtilityModel.toModel(customer, customerRequest);
        Customer updatedCustomer = repository.save(customer);
        return UtilityModel.fromModel(updatedCustomer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        validate.validate(customerId);
        Customer customer = repository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        repository.delete(customer);
    }


    @Override
    public Mono<CustomerResponse> getCustomer(String customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        return Mono.just(UtilityModel.fromModel(customer));
    }

}
