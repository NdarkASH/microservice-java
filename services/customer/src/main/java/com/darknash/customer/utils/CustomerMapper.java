package com.darknash.customer.utils;

import com.darknash.customer.document.Customer;
import com.darknash.customer.dto.CustomerRequest;
import com.darknash.customer.dto.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

}
