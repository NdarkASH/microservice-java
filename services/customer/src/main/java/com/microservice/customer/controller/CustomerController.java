package com.microservice.customer.controller;

import com.microservice.customer.dto.AppResponse;
import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import com.microservice.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {

        CustomerResponse response = customerService.createCustomer(customerRequest);

        return AppResponse.<CustomerResponse>builder()
                .data(response)
                .message("Customer created")
                .build();
    }
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AppResponse<Mono<CustomerResponse>> getCustomer(@PathVariable String id) {
        Mono<CustomerResponse> response = customerService.getCustomer(id);

        return AppResponse.<Mono<CustomerResponse>>builder()
                .data(response)
                .message("Customer found")
                .build();
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppResponse<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.updateCustomer(id, customerRequest);
        return AppResponse.<CustomerResponse>builder()
                .data(response)
                .message("Customer updated")
                .build();
    }

    @DeleteMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AppResponse<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);

        return AppResponse.<Void>builder()
                .message("Customer deleted")
                .build();

    }


}
