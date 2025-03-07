package com.base.order.api.controller.dto;

import java.util.List;

public record ApiResponseOrders<T>(List<T> data,
                             PaginationResponse pagination) {
}
