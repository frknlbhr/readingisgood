package com.filbahar.readingisgood.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filbahar.readingisgood.controller.BookController;
import com.filbahar.readingisgood.exception.GlobalExceptionHandler;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author filbahar
 * @created 30.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class BookControllerIT {

    private MockMvc mockMvc;

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    private JacksonTester<Book> jsonBook;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testSave() throws Exception {
        Book book = new Book();
        book.setName("bookName");
        Mockito.when(bookService.saveBook(Mockito.isA(Book.class))).thenReturn(book);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/book/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook.write(book).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testUpdateStockEndpoint() throws Exception {
        Book book = new Book();
        book.setName("bookName");
        Mockito.when(bookService.updateStock(Mockito.anyString(), Mockito.anyInt())).thenReturn(book);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/book/bookId/stock/250")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonBook.write(book).getJson());
    }

}
