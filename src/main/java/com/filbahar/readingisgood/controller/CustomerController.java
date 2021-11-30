package com.filbahar.readingisgood.controller;

import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.service.CustomerService;
import com.filbahar.readingisgood.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create new customer or update existing one")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(value = "/save")
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
        Customer persistedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(persistedCustomer, HttpStatus.OK);
    }

    @ApiOperation(value = "Find orders of a customer")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(value = "/{customerId}/orders")
    public ResponseEntity<Page<Order>> findOrdersByCustomerId(@PathVariable String customerId, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Page<Order> orders = orderService.findOrderByCustomerId(customerId, PageRequest.of(page, limit));
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
