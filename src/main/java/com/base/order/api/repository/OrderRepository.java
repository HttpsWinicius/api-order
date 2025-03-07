package com.base.order.api.repository;


import com.base.order.api.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);

    static Aggregation getAggregations(Long costumerId) {

        return newAggregation(
                match(Criteria.where("customerId").is(costumerId)),
                group().sum("total").as("total")
        );
    }
}
