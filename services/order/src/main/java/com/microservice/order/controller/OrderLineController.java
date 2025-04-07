package com.microservice.order.controller;

import com.microservice.order.dto.AppResponse;
import com.microservice.order.dto.OrderLineResponse;
import com.microservice.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService service;

    @GetMapping(path = "/order/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public AppResponse<List<OrderLineResponse>> findByOrderLine(@PathVariable Integer order_id) {

        List<OrderLineResponse> responseList = service.findAllOrderLineByOrderId(order_id);

        return AppResponse.<List<OrderLineResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(responseList)
                .build();

    }


}
