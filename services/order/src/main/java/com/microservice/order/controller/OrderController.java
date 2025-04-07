package com.microservice.order.controller;

import com.microservice.order.dto.AppResponse;
import com.microservice.order.dto.OrderRequest;
import com.microservice.order.dto.OrderResponse;
import com.microservice.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponse<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        Integer response = orderService.addOrder(request);
        return AppResponse.<Integer>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.toString())
                .data(response)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public AppResponse<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponseList = orderService.findAllOrders();

        return AppResponse.<List<OrderResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .data(orderResponseList)
                .build();
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public AppResponse<OrderResponse> getOrderById(@PathVariable Integer id) {
        OrderResponse orderResponse = orderService.findOrderById(id);

        return AppResponse.<OrderResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .data(orderResponse)
                .build();
    }

}
