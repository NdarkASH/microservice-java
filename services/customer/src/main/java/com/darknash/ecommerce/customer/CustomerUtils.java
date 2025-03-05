package com.darknash.ecommerce.customer;

import ch.qos.logback.core.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    * ini utils buat mapping sekaligus validasi data request
    * if pertama validasi firstname
    * if kedua validasi email
    * if ketiga buat validasi data adress
 */
@Configuration
public class CustomerUtils {
    @Bean
    public void merge(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }
}
