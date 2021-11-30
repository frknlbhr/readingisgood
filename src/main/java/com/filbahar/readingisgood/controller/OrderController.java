package com.filbahar.readingisgood.controller;

import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.model.OrderStatus;
import com.filbahar.readingisgood.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create new order")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(value = "/save")
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) throws OperationNotSupportedException {
        order.setOrderStatus(OrderStatus.PREPARING);
        Order persisted = orderService.saveOrder(order);
        return new ResponseEntity<>(persisted, HttpStatus.OK);
    }

    @ApiOperation(value = "Find order by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(value = "find")
    public ResponseEntity<Order> findById(@RequestParam("id") String id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(value = "Find orders by date between")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(value = "/orders")
    public ResponseEntity<List<Order>> findByOrderDateBetween(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                              @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Order> orders = orderService.findOrdersByOrderDateBetween(startDate.atStartOfDay(), endDate.atStartOfDay());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
