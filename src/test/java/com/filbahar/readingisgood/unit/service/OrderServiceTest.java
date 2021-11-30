package com.filbahar.readingisgood.unit.service;

import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.exception.NotEnoughStockException;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.model.Order;
import com.filbahar.readingisgood.model.OrderStatus;
import com.filbahar.readingisgood.repo.OrderRepository;
import com.filbahar.readingisgood.service.BookService;
import com.filbahar.readingisgood.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order orderFromDb;
    private Book bookFromDb;
    private List<Book> booksFromDb = new ArrayList<>();

    @BeforeEach
    public void setup() {
        orderFromDb = new Order();
        orderFromDb.setId("id");
        orderFromDb.setOrderStatus(OrderStatus.PREPARING);
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> booksOfOrder = new ArrayList<>();
        booksOfOrder.add(book1);
        booksOfOrder.add(book2);
        orderFromDb.setBooks(booksOfOrder);
        orderFromDb.setNumberOfBooks(booksOfOrder.size());
        Customer customer = new Customer();
        customer.setId("customerId");
        orderFromDb.setCustomer(customer);
        orderFromDb.setOrderDate(LocalDateTime.now());

        bookFromDb = new Book();
        bookFromDb.setNumberInStock(140);
        bookFromDb.setPrice(BigDecimal.ONE);
        booksFromDb.add(bookFromDb);
    }

    @Test
    public void test_saveOrder() throws OperationNotSupportedException {
        Mockito.when(orderRepository.save(Mockito.isA(Order.class))).thenReturn(orderFromDb);
        Mockito.when(bookService.saveBooks(Mockito.anyList())).thenReturn(booksFromDb);
        Mockito.when(bookService.findBookListByIds(Mockito.anyList())).thenReturn(booksFromDb);

        Order order = new Order();
        Book book = new Book();
        book.setId("bookId");
        book.setNumberInStock(23);
        book.setPrice(BigDecimal.ONE);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        order.setBooks(bookList);
        Order result = orderService.saveOrder(order);
        Assertions.assertEquals(orderFromDb.getId(), result.getId());
        Assertions.assertEquals(orderFromDb.getOrderDate(), result.getOrderDate());
        Assertions.assertEquals(orderFromDb.getBooks().size(), result.getBooks().size());
        Assertions.assertEquals(orderFromDb.getTotalPrice(), result.getTotalPrice());
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.isA(Order.class));
    }

    @Test
    public void test_saveOrder_fail() {
        Assertions.assertThrows(OperationNotSupportedException.class, () -> orderService.saveOrder(orderFromDb));
    }

    @Test
    public void test_saveOrder_stock_fail() {
        Book book1 = new Book();
        book1.setId("id1");
        book1.setNumberInStock(15);
        book1.setPrice(BigDecimal.TEN);
        Book book2 = new Book();
        book2.setId("id2");
        book2.setNumberInStock(0);
        book2.setPrice(BigDecimal.ONE);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Order order = new Order();
        order.setBooks(books);
        Mockito.when(bookService.findBookListByIds(Mockito.anyList())).thenReturn(books);
        Assertions.assertThrows(NotEnoughStockException.class, () -> orderService.saveOrder(order));
    }

    @Test
    public void test_findOrderById_success() {
        Mockito.when(orderRepository.findById(Mockito.anyString())).thenReturn(Optional.of(orderFromDb));

        Order result = orderService.findOrderById("id");
        Assertions.assertEquals(orderFromDb.getId(), result.getId());
        Assertions.assertEquals(orderFromDb.getOrderDate(), result.getOrderDate());
        Assertions.assertEquals(orderFromDb.getBooks().size(), result.getBooks().size());
        Assertions.assertEquals(orderFromDb.getCustomer().getId(), result.getCustomer().getId());
        Mockito.verify(orderRepository, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    public void test_findOrderById_fail() {
        Mockito.when(orderRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.findOrderById("id"));
    }

}
