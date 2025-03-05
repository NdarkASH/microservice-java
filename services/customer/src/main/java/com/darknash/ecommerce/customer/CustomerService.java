package com.darknash.ecommerce.customer;

import com.darknash.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerUtils utils;

    public String createCustomer(CustomerRequest request) {
        var customer = this.customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }
    public void updateCustomer(CustomerRequest request) {
        var customer = this.customerRepository.findById(request.id()).orElseThrow(
                ()-> new CustomerNotFoundException(
                        String.format("Customer with id '%s' not found", request.id())
                ));
        utils.merge(customer, request);
        this.customerRepository.save(customer);
    }
}
