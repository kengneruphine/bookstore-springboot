package com.ruphine.bookstore.controllers;

import com.ruphine.bookstore.dto.AuthorDto;
import com.ruphine.bookstore.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    @PostMapping()
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto){
        AuthorDto savedAuthor = authorService.createAuthor(authorDto);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllAuthors(){
        return new ResponseEntity<>(authorService.fetchAuthorDtoList(), HttpStatus.OK);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId){
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PutMapping("{authorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long authorId,
                                              @RequestBody AuthorDto updatedAuthor){
        AuthorDto authorDto = authorService.updateAuthor(authorId, updatedAuthor);
        return ResponseEntity.ok(authorDto);
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author deleted successfully");
    }

    @GetMapping("{bookId}/books")
    public ResponseEntity<List<AuthorDto>> getAllAuthorsByBook(@PathVariable Long bookId){
        return new ResponseEntity<>(authorService.getAuthorsByBookId(bookId), HttpStatus.OK);
    }
}
