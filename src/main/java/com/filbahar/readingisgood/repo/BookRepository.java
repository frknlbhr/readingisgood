package com.filbahar.readingisgood.repo;

import com.filbahar.readingisgood.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Repository
public interface BookRepository extends MongoRepository<Book, String> {



}
