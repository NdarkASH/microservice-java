package com.darknash.customer.dto;

import com.darknash.customer.document.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "Must fill the first name")
        String firstName,

        @NotNull(message = "Must fill the last name")
        String lastName,

        @Email
        @NotNull(message = "Must fill the email address")
        String email,

        Address address
) {
}
