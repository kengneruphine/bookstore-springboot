package com.ruphine.bookstore.controllers;

import com.ruphine.bookstore.dto.AuthorDto;
import com.ruphine.bookstore.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
@Tag(name = "Author Controller", description = "APIs for managing authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private final AuthorService authorService;

    @PostMapping()
    @Operation(summary = "Create a new author", description = "Adds a new author to the system and returns the created author's information.")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto savedAuthor = authorService.createAuthor(authorDto);
        logger.info("Author created: {}", savedAuthor);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors available in the system.")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        logger.info("Fetching all authors");
        return new ResponseEntity<>(authorService.fetchAuthorDtoList(), HttpStatus.OK);
    }

    @GetMapping("{authorId}")
    @Operation(summary = "Get author by ID", description = "Returns the details of a single author using their unique ID.")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
        logger.info("Fetching author with ID: {}", authorId);
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PutMapping("{authorId}")
    @Operation(summary = "Update an author", description = "Updates the information of an existing author based on the given ID.")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long authorId,
            @RequestBody AuthorDto updatedAuthor) {
        logger.info("Updating author with ID: {}", authorId);
        AuthorDto authorDto = authorService.updateAuthor(authorId, updatedAuthor);
        return ResponseEntity.ok(authorDto);
    }

    @DeleteMapping("{authorId}")
    @Operation(summary = "Delete an author", description = "Deletes the specified author from the system using their ID.")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        logger.info("Deleting author with ID: {}", authorId);
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author deleted successfully");
    }

    @GetMapping("{bookId}/books")
    @Operation(summary = "Get authors by book ID", description = "Retrieves all authors who contributed to a specific book using the book's ID.")
    public ResponseEntity<List<AuthorDto>> getAllAuthorsByBook(@PathVariable Long bookId) {
        logger.info("Fetching authors for book with ID: {}", bookId);
        return new ResponseEntity<>(authorService.getAuthorsByBookId(bookId), HttpStatus.OK);
    }
}
