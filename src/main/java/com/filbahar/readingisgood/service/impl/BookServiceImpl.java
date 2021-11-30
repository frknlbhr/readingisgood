package com.filbahar.readingisgood.service.impl;

import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.model.Book;
import com.filbahar.readingisgood.repo.BookRepository;
import com.filbahar.readingisgood.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " not found"));
    }

    @Override
    public Book updateStock(String bookId, int newStock) {
        Book book = findBookById(bookId);
        book.setNumberInStock(newStock);
        return saveBook(book);
    }

    @Override
    public List<Book> findBookListByIds(List<String> bookIds) {
        if (!CollectionUtils.isEmpty(bookIds)) {
            return bookIds.stream().map(this::findBookById).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
