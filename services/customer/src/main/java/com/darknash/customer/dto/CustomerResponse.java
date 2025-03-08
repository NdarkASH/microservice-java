package com.darknash.customer.dto;

import com.darknash.customer.document.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
