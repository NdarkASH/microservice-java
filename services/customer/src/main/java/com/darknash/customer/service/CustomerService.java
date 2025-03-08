package com.darknash.customer.service;

import com.darknash.customer.dto.CustomerRequest;
import com.darknash.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);
    void updateCustomer(CustomerRequest request);
    List<CustomerResponse> findAllCustomers();
    CustomerResponse findCustomerById(String id);
    Boolean findExistCustomerById(String id);
    void deleteCustomerById(String id);
}
