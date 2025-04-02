package com.microservice.customer.utility;

import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import com.microservice.customer.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilityModel {
    public void toModel(Customer customer, CustomerRequest request) {
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
    }

    public CustomerResponse fromModel(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
