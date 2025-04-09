package com.ruphine.bookstore.repositories;

import com.ruphine.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id= :bookId")
    List<Author> findAuthorsByBookId(@Param("bookId") Long bookId);
}
