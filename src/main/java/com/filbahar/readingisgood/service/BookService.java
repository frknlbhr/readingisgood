package com.filbahar.readingisgood.service;

import com.filbahar.readingisgood.model.Book;

import java.util.List;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public interface BookService {

    Book saveBook(Book book);

    List<Book> saveBooks(List<Book> books);

    Book findBookById(String id);

    Book updateStock(String bookId, int newStock);

    List<Book> findBookListByIds(List<String> bookIds);

}
