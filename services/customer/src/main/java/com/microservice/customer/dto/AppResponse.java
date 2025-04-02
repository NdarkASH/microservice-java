package com.microservice.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResponse<T> {

    private String message;
    private T data;
    @JsonIgnore
    private PagingResponse pagingResponse;


}
