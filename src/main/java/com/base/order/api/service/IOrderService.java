package com.base.order.api.service;

import com.base.order.api.controller.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

public interface IOrderService {

    Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest);

    BigDecimal findTotalOrdersByCustomerId(Long customerId);

}
