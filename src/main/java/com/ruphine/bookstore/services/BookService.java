package com.ruphine.bookstore.services;


import com.ruphine.bookstore.dto.BookDto;

import java.util.List;

/**
 * Service Interface for Book entity
 * Defines methods for CRUD operations and additional business logic
 */
public interface BookService {

    /**
     * Saves a book item.
     * @param BookDto, the bookDto to save
     * @return the saved bookDto
     */
     BookDto saveBook(BookDto bookDto);

    /**
     * Fetches the list of all bookDtos
     * @return a list of BookDtos
     */
    List<BookDto> fetchBookDtoList();

    /**
     * Updates an existing book
     * @param updatedBook, the bookDto with the updated information
     * @param bookId the ID of the bookDto to update
     * @return the updated Book
     */
    BookDto updateBook(Long bookId, BookDto updatedBook);

    /**
     * Find a Book by id
     */
    BookDto getBookById(Long bookId);

    /**
     * Deletes a book entity by its ID
     * @param, bookId, the Id of the entity to be deleted
     */

    void deleteBookById(Long bookId);

    /**
     * Find all Books written by an author
     * @param authorId
     */
    List<BookDto> getBooksByAuthorId(Long authorId);
}
