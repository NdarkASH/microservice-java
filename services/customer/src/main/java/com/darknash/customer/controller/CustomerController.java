package com.darknash.customer.controller;

import com.darknash.customer.dto.CustomerRequest;
import com.darknash.customer.dto.CustomerResponse;
import com.darknash.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;


    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id") String id) {
        return ResponseEntity.ok(service.findExistCustomerById(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        return ResponseEntity.accepted().build();
    }
}
