package com.filbahar.readingisgood.service.impl;

import com.filbahar.readingisgood.dto.OrderStatisticsDto;
import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.exception.NotEnoughStockException;
import com.filbahar.readingisgood.model.BaseEntity;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.repo.OrderRepository;
import com.filbahar.readingisgood.service.BookService;
import com.filbahar.readingisgood.service.CustomerService;
import com.filbahar.readingisgood.service.OrderService;
import com.filbahar.readingisgood.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final BookService bookService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) throws OperationNotSupportedException {
        if (order.getId() != null) {
            throw new OperationNotSupportedException("Update Order entity operation is not supported");
        }
        List<Book> orderedBooks = bookService.findBookListByIds(order.getBooks().stream().map(BaseEntity::getId).collect(Collectors.toList()));
        BigDecimal totalPriceOfOrder = BigDecimal.ZERO;
        for (Book orderedBook : orderedBooks) {
            int stock = orderedBook.getNumberInStock();
            if (stock < 1) {
                throw new NotEnoughStockException(orderedBook.getId(), orderedBook.getName());
            }
            orderedBook.setNumberInStock(stock - 1);
            totalPriceOfOrder = totalPriceOfOrder.add(orderedBook.getPrice());
        }
        order.setTotalPrice(totalPriceOfOrder);
        order.setBooks(bookService.saveBooks(orderedBooks));
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with id = " + id + " not found"));
    }

    @Override
    public Page<Order> findOrderByCustomerId(String customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public Page<Order> findOrderByCustomerEmail(String customerEmail, Pageable pageable) {
        Customer customer = customerService.findCustomerByEmail(customerEmail);
        if (customer != null) {
            return findOrderByCustomerId(customer.getId(), pageable);
        }
        return null;
    }

    @Override
    public List<Order> findOrdersByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    @Override
    public OrderStatisticsDto findMonthlyOrderStatistic(int month, int year) {
        LocalDateTime startOfMonth = DateTimeUtils.getStartDateTimeOfMonth(month, year);
        LocalDateTime endOfMonth = DateTimeUtils.getEndDateTimeOfMonth(month, year);

        List<Order> ordersMonthly = findOrdersByOrderDateBetween(startOfMonth, endOfMonth);
        if (!CollectionUtils.isEmpty(ordersMonthly)) {
            int totalOrderCount = ordersMonthly.size();
            int totalBookCount = 0;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Order order : ordersMonthly) {
                totalBookCount += order.getNumberOfBooks();
                totalPrice = totalPrice.add(order.getTotalPrice());
            }
            return new OrderStatisticsDto(month, year, totalOrderCount, totalBookCount, totalPrice);
        }
        return new OrderStatisticsDto(month, year, 0, 0, BigDecimal.ZERO);
    }
}
