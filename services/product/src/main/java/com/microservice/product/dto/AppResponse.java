package com.microservice.product.dto;

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
    private int code;
    private String message;
    private T data;
    @JsonIgnore
    private PagingResponse page;
}
