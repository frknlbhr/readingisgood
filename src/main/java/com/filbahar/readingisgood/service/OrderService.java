package com.filbahar.readingisgood.service;

import com.filbahar.readingisgood.dto.OrderStatisticsDto;
import com.filbahar.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public interface OrderService {

    Order saveOrder(Order order) throws OperationNotSupportedException;

    Order findOrderById(String id);

    Page<Order> findOrderByCustomerId(String customerId, Pageable pageable);

    Page<Order> findOrderByCustomerEmail(String customerEmail, Pageable pageable);

    List<Order> findOrdersByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    OrderStatisticsDto findMonthlyOrderStatistic(int month, int year);

}
