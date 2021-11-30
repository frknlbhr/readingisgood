package com.filbahar.readingisgood.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filbahar.readingisgood.controller.CustomerController;
import com.filbahar.readingisgood.exception.GlobalExceptionHandler;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.service.impl.CustomerServiceImpl;
import com.filbahar.readingisgood.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author filbahar
 * @created 30.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class CustomerControllerIT {

    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private CustomerController customerController;

    private JacksonTester<Customer> jsonCustomer;
    private JacksonTester<Page<Order>> jsonPageOrder;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testSave() throws Exception {
        Customer customer = new Customer();
        customer.setId("customerId");
        customer.setFirstName("ali");
        customer.setLastName("veli");
        customer.setEmail("customer@email.com");
        Mockito.when(customerService.saveCustomer(Mockito.isA(Customer.class))).thenReturn(customer);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCustomer.write(customer).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testfindOrdersByCustomerId() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        Page<Order> ordersPage = new PageImpl<>(orders);
        Mockito.when(orderService.findOrderByCustomerId(Mockito.anyString(), Mockito.isA(Pageable.class))).thenReturn(ordersPage);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/customerId/orders")
                        .queryParam("page", "0")
                        .queryParam("limit", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isEqualTo(jsonPageOrder.write(ordersPage).getJson());
    }
}
