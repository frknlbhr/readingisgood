package com.filbahar.readingisgood.unit.service;

import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.repo.BookRepository;
import com.filbahar.readingisgood.service.impl.BookServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book bookFromMockedRepo;
    private Book anotherBookFromMockedRepo;
    private List<Book> bookListFromMockedRepo = new ArrayList<>();

    @BeforeEach
    public void setup() {
        bookFromMockedRepo = new Book();
        bookFromMockedRepo.setId("idFromDb");
        bookFromMockedRepo.setNumberInStock(100);
        bookFromMockedRepo.setName("Puslu Kıtalar Atlası");
        bookFromMockedRepo.setAuthor("İhsan Oktay Anar");

        anotherBookFromMockedRepo = new Book();
        anotherBookFromMockedRepo.setId("id2FromDb");
        anotherBookFromMockedRepo.setName("Gazavat-ı Hayreddin Paşa");
        anotherBookFromMockedRepo.setAuthor("Seyyid Muradi");
        anotherBookFromMockedRepo.setNumberInStock(25);

        bookListFromMockedRepo.add(bookFromMockedRepo);
        bookListFromMockedRepo.add(anotherBookFromMockedRepo);
    }

    @Test
    public void test_saveBook() {
        Mockito.when(bookRepository.save(Mockito.isA(Book.class))).thenReturn(bookFromMockedRepo);

        Book result =bookService.saveBook(new Book());
        Assertions.assertEquals(bookFromMockedRepo.getId(), result.getId());
        Assertions.assertEquals(bookFromMockedRepo.getName(), result.getName());
        Assertions.assertEquals(bookFromMockedRepo.getAuthor(), result.getAuthor());
        Assertions.assertEquals(bookFromMockedRepo.getNumberInStock(), result.getNumberInStock());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.isA(Book.class));
    }

    @Test
    public void test_saveBooks() {
        Mockito.when(bookRepository.saveAll(Mockito.anyList())).thenReturn(bookListFromMockedRepo);

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        List<Book> resultList = bookService.saveBooks(bookList);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(bookListFromMockedRepo.size(), resultList.size());
        Mockito.verify(bookRepository, Mockito.times(1)).saveAll(Mockito.anyList());
    }

    @Test
    public void test_findBookById_success() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(bookFromMockedRepo));

        Book result = bookService.findBookById("id");
        Assertions.assertEquals(bookFromMockedRepo.getId(), result.getId());
        Assertions.assertEquals(bookFromMockedRepo.getName(), result.getName());
        Assertions.assertEquals(bookFromMockedRepo.getAuthor(), result.getAuthor());
        Assertions.assertEquals(bookFromMockedRepo.getNumberInStock(), result.getNumberInStock());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    public void test_findBookById_fail() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> bookService.findBookById("id"));
    }

    @Test
    public void test_updateStock() {
        Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(bookFromMockedRepo));
        Mockito.when(bookRepository.save(Mockito.isA(Book.class))).thenReturn(bookFromMockedRepo);

        int initialStockNumber = bookFromMockedRepo.getNumberInStock();
        Book result = bookService.updateStock("id", initialStockNumber + 100);
        Assertions.assertEquals(initialStockNumber + 100, result.getNumberInStock());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyString());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.isA(Book.class));
    }

}
