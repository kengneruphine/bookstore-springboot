package com.ruphine.bookstore.repositories;

import com.ruphine.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
