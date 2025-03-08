package com.darknash.customer.document;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Getter
@Setter
@Builder
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
