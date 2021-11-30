package com.filbahar.readingisgood.controller;

import com.filbahar.readingisgood.dto.OrderStatisticsDto;
import com.filbahar.readingisgood.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private final OrderService orderService;

    @Autowired
    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Calculate monthly order statistics")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(value = "/order/monthly")
    public ResponseEntity<OrderStatisticsDto> calculateMonthlyOrderStatistic(@RequestParam("month") int month, @RequestParam("year") int year) {
        OrderStatisticsDto orderStatisticsDto = orderService.findMonthlyOrderStatistic(month, year);
        return new ResponseEntity<>(orderStatisticsDto, HttpStatus.OK);
    }
}
