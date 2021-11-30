package com.filbahar.readingisgood.controller;

import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Create new book or update existing one")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(value = "/save")
    public ResponseEntity<Book> save(@Valid @RequestBody Book book) {
        Book persistedBook = bookService.saveBook(book);
        return new ResponseEntity<>(persistedBook, HttpStatus.OK);
    }

    @ApiOperation(value = "Update stock value of a book")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
                           @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping(value = "/{bookId}/stock/{stock}")
    public ResponseEntity<Book> updateStock(@PathVariable String bookId, @PathVariable int stock) {
        Book updatedBook = bookService.updateStock(bookId, stock);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
}
