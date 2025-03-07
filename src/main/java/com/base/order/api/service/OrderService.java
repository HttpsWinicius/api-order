package com.base.order.api.service;

import com.base.order.api.controller.dto.OrderResponse;
import com.base.order.api.entity.OrderEntity;
import com.base.order.api.repository.OrderRepository;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {

        Page<OrderEntity> orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }


    public BigDecimal findTotalOrdersByCustomerId(Long costumerId) {

        Aggregation aggregations = OrderRepository.getAggregations(costumerId);

        AggregationResults<Document> response = mongoTemplate.aggregate(aggregations,
                "tbl_orders",
                Document.class);

        return getValueTotalOrdersByCostumerId(response);

    }

    private BigDecimal getValueTotalOrdersByCostumerId(AggregationResults<Document> response) {
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }
}
