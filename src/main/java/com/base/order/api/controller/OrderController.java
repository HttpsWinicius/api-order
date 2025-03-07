package com.base.order.api.controller;

import com.base.order.api.controller.dto.ApiResponseOrders;
import com.base.order.api.controller.dto.ApiResponseValueTotalOrders;
import com.base.order.api.controller.dto.OrderResponse;
import com.base.order.api.controller.dto.PaginationResponse;
import com.base.order.api.service.IOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrderController {

    public final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<ApiResponseOrders<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId,
                                                                       @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "page", defaultValue = "10") Integer pageSize) {

        Page<OrderResponse> pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        return ResponseEntity.ok(new ApiResponseOrders<>(
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));

    }

    @GetMapping("customer/{customerId}/orders/value/total")
    public ResponseEntity<ApiResponseValueTotalOrders> totalValueOrdersByCustomer(@PathVariable("customerId") Long customerId) {

        BigDecimal totalOnOrders = orderService.findTotalOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponseValueTotalOrders(totalOnOrders));

    }


}
