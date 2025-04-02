package com.microservice.customer.service;

import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import reactor.core.publisher.Mono;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(String id, CustomerRequest customerRequest);
    void deleteCustomer(String id);
    Mono<CustomerResponse> getCustomer(String id);

}
