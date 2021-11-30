package com.filbahar.readingisgood.repo;

import com.filbahar.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Page<Order> findByCustomerId(String customerId, Pageable pageable);

}
