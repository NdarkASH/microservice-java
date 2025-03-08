package com.darknash.customer.service.Impl;

import com.darknash.customer.document.Customer;
import com.darknash.customer.dto.CustomerRequest;
import com.darknash.customer.dto.CustomerResponse;
import com.darknash.customer.exception.CustomerNotFoundException;
import com.darknash.customer.repository.CustomerRepository;
import com.darknash.customer.service.CustomerService;
import com.darknash.customer.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toCustomer(request);
        repository.save(customer);
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = repository.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with provided ID::%s", request.id())
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findCustomerById(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Cannot find customer with provided ID::%s", id)));
    }

    public Boolean findExistCustomerById(String id) {
        return repository.findById(id).isPresent();
    }

    public void deleteCustomerById(String id) {
        repository.deleteById(id);
    }



    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isEmpty(customer.getFirstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isEmpty(customer.getEmail())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }

    }
}
