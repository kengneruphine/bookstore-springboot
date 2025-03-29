package com.ruphine.bookstore.services;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.entities.Book;
import com.ruphine.bookstore.enums.BookGenre;
import com.ruphine.bookstore.exception.ResourceNotFoundException;
import com.ruphine.bookstore.mapper.BookMapper;
import com.ruphine.bookstore.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = BookMapper.mapToBookEntity(bookDto);
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

        if(updatedBook.getTitle() != null && !updatedBook.getTitle().isEmpty()){
            existingBook.setTitle(updatedBook.getTitle());
        }

        if(updatedBook.getGenre() != null && updatedBook.getGenre() != BookGenre.UNKNOWN){
            existingBook.setGenre(updatedBook.getGenre());
        }

        if(updatedBook.getPublishDate() != null){
            existingBook.setPublishDate(updatedBook.getPublishDate());
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
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with given id : " + bookId));
        bookRepository.deleteById(bookId);
    }
}
