package com.darknash.customer.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Customer {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

}
