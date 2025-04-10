package com.ruphine.bookstore.controllers;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")   //defining the base Url
public class BookController {

    @Autowired
    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto){
        BookDto savedBook = bookService.saveBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.fetchBookDtoList(), HttpStatus.OK);
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long bookId,
                                              @RequestBody BookDto updatedBook){
        BookDto bookDto = bookService.updateBook(bookId, updatedBook);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @GetMapping("{authorId}/authors")
    public ResponseEntity<List<BookDto>> getAllBooksByAuthor(@PathVariable Long authorId){
        return new ResponseEntity<>(bookService.getBooksByAuthorId(authorId), HttpStatus.OK);
    }
}
