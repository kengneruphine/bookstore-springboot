package com.ruphine.bookstore.services;

import com.ruphine.bookstore.dto.AuthorDto;
import com.ruphine.bookstore.dto.BookDto;

import java.util.List;

/**
 * Service Interface for Author entity
 * Defines methods for CRUD operations and additional business logic
 */
public interface AuthorService {
    /**
     * Create an author
     * @param authorDto, the AuthorDto to save
     * @return the saved AuthorDto
     */
    AuthorDto createAuthor(AuthorDto authorDto);

    /**
     * Fetches the list of all AuthorDtos
     * @return a list of AuthorDtos
     */
    List<AuthorDto> fetchAuthorDtoList();

    /**
     * Updates an existing author
     * @param updatedAuthor, the authorDto with the updated information
     * @param authorId the ID of the authorDto to update
     * @return the updated Author
     */
    AuthorDto updateAuthor(Long authorId, AuthorDto updatedAuthor);

    /**
     * Find a Author by id
     */
    AuthorDto getAuthorById(Long authorId);

    /**
     * Deletes a author entity by its ID
     * @param, authorId, the Id of the entity to be deleted
     */

    void deleteAuthorById(Long authorId);


    /**
     * Find all Author who wrote a book
     * @param bookId
     */
    List<AuthorDto> getAuthorsByBookId(Long bookId);
}
