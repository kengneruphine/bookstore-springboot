package com.ruphine.bookstore.services;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.entities.Author;
import com.ruphine.bookstore.entities.Book;
import com.ruphine.bookstore.enums.BookGenre;
import com.ruphine.bookstore.exception.ResourceNotFoundException;
import com.ruphine.bookstore.mapper.BookMapper;
import com.ruphine.bookstore.repositories.AuthorRepository;
import com.ruphine.bookstore.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = BookMapper.mapToBookEntity(bookDto);

        // Initialize author list if null
        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            Set<Author> savedAuthors = new HashSet<>();

            for (Author author : book.getAuthors()) {
                if (author.getId() == null) { // New author (no ID provided)
                    author = authorRepository.save(author);
                }
                savedAuthors.add(author);
            }

            book.setAuthors(savedAuthors);
        }


        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToBookDto(savedBook);
    }

    @Override
    public List<BookDto> fetchBookDtoList() {
        return bookRepository.findAll().stream().map(BookMapper::mapToBookDto).toList();
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with given id : " + bookId));

        if (updatedBook.getTitle() != null && !updatedBook.getTitle().isEmpty()) {
            existingBook.setTitle(updatedBook.getTitle());
        }

        if (updatedBook.getGenre() != null && updatedBook.getGenre() != BookGenre.UNKNOWN) {
            existingBook.setGenre(updatedBook.getGenre());
        }

        if (updatedBook.getPublishDate() != null) {
            existingBook.setPublishDate(updatedBook.getPublishDate());
        }

        //Updating the authors list
        if (updatedBook.getAuthors() != null && !updatedBook.getAuthors().isEmpty()) {
            Set<Author> existingAuthors = existingBook.getAuthors();
            if (existingAuthors == null) {
                existingAuthors = new HashSet<>();
            }

            for (Author authorDto : updatedBook.getAuthors()) {
                Author author = authorRepository.findById(authorDto.getId())
                        .orElseGet(() -> authorRepository.save(
                                new Author(    // Either fetch existing or create a new author
                                        authorDto.getFirstName(),
                                        authorDto.getLastName(),
                                        authorDto.getEmail()
                                )));
                existingAuthors.add(author);
            }
            existingBook.setAuthors(existingAuthors);
        }

        Book updatedBookObj = bookRepository.save(existingBook);
        return BookMapper.mapToBookDto(updatedBookObj);
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with given id : " + bookId));
        return BookMapper.mapToBookDto(book);
    }


    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with given id : " + bookId));
        bookRepository.deleteById(bookId);
    }

    public List<BookDto> getBooksByAuthorId(Long authorId){
        if(!authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException("Author does not exist with given id : " + authorId);
        }

        return bookRepository.findBooksByAuthorId(authorId).stream().map(BookMapper::mapToBookDto).toList();
    }
}
