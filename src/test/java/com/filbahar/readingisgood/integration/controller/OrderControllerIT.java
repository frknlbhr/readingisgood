package com.filbahar.readingisgood.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filbahar.readingisgood.controller.OrderController;
import com.filbahar.readingisgood.exception.GlobalExceptionHandler;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author filbahar
 * @created 30.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class OrderControllerIT {

    private MockMvc mockMvc;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    private JacksonTester<Order> jsonOrder;
    private JacksonTester<Page<Order>> jsonPageOrder;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testSave() throws Exception {
        Order order = new Order();
        order.setId("orderId");
        //order.setOrderDate(LocalDateTime.now());
        Customer customer = new Customer();
        customer.setFirstName("ali");
        customer.setLastName("veli");
        customer.setEmail("customer@email.com");
        order.setCustomer(customer);
        order.setNumberOfBooks(1);
        order.setTotalPrice(BigDecimal.TEN);
        Book book = new Book();
        book.setName("bookname");
        List<Book> books = new ArrayList<>();
        books.add(book);
        order.setBooks(books);
        //Mockito.when(orderService.saveOrder(Mockito.isA(Order.class))).thenReturn(order);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/order/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOrder.write(order).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        //assertThat(response.getContentAsString()).isEqualTo(jsonOrder.write(order).getJson());
    }


}
