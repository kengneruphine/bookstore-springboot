package com.ruphine.bookstore.controllers;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Controller", description = "APIs for managing books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private final BookService bookService;

    @PostMapping()
    @Operation(summary = "Create a new book", description = "Adds a new book with its author(s)")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.saveBook(bookDto);
        logger.info("Book created: {}", savedBook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all books", description = "Retrieves a list of all books in the bookstore.")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        logger.info("Fetching all books");
        return new ResponseEntity<>(bookService.fetchBookDtoList(), HttpStatus.OK);
    }

    @GetMapping("{bookId}")
    @Operation(summary = "Get book by ID", description = "Returns the details of a single book by its unique ID.")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        logger.info("Fetching book with ID: {}", bookId);
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("{bookId}")
    @Operation(summary = "Update a book", description = "Updates an existing book's information using its ID.")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long bookId,
            @RequestBody BookDto updatedBook) {
        logger.info("Updating book with ID: {}", bookId);
        BookDto bookDto = bookService.updateBook(bookId, updatedBook);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("{bookId}")
    @Operation(summary = "Delete a book", description = "Deletes a book from the store by its ID.")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @GetMapping("{authorId}/authors")
    @Operation(summary = "Get books by author", description = "Retrieves a list of all books written by a specific author using the author's ID.")
    public ResponseEntity<List<BookDto>> getAllBooksByAuthor(@PathVariable Long authorId) {
        logger.info("Fetching books for author with ID: {}", authorId);
        return new ResponseEntity<>(bookService.getBooksByAuthorId(authorId), HttpStatus.OK);
    }
}
