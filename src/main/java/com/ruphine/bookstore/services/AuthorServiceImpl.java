package com.ruphine.bookstore.services;

import com.ruphine.bookstore.dto.AuthorDto;
import com.ruphine.bookstore.entities.Author;
import com.ruphine.bookstore.entities.Book;
import com.ruphine.bookstore.exception.ResourceNotFoundException;
import com.ruphine.bookstore.mapper.AuthorMapper;
import com.ruphine.bookstore.repositories.AuthorRepository;
import com.ruphine.bookstore.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = AuthorMapper.mapToAuthorEntity(authorDto);
        Author createdAuthor = authorRepository.save(author);
        return AuthorMapper.mapToAuthorDto(createdAuthor);
    }

    @Override
    public List<AuthorDto> fetchAuthorDtoList() {
        return authorRepository.findAll().stream().map(AuthorMapper::mapToAuthorDto).toList();
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto updatedAuthor) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author does not exist with given id : " + authorId));

        if(updatedAuthor.getFirstName() != null && !updatedAuthor.getFirstName().isEmpty()){
            existingAuthor.setFirstName(updatedAuthor.getFirstName());
        }

        if(updatedAuthor.getLastName() != null && !updatedAuthor.getLastName().isEmpty()){
            existingAuthor.setLastName(updatedAuthor.getLastName());
        }

        if(updatedAuthor.getEmail() != null && !updatedAuthor.getEmail().isEmpty()){
            existingAuthor.setEmail(updatedAuthor.getEmail());
        }

        //checking the book list
        if(updatedAuthor.getBooks() !=null && !updatedAuthor.getBooks().isEmpty()) {
            Set<Book> existingBooks = existingAuthor.getBooks();
            if (existingBooks == null) {
                existingBooks = new HashSet<>();
            }

            for (Book bookDto : updatedAuthor.getBooks()) {
                Book book = bookRepository.findById(bookDto.getId())
                        .orElseGet(() -> bookRepository.save(new Book(
                                bookDto.getTitle(),
                                bookDto.getGenre(),
                                bookDto.getPublishDate())));
                existingBooks.add(book); // add to existing set (no duplicates if using Set)
            }
            existingAuthor.setBooks(existingBooks);
        }

        Author updatedAuthorObj = authorRepository.save(existingAuthor);
        return AuthorMapper.mapToAuthorDto(updatedAuthorObj);
    }

    @Override
    public AuthorDto getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author does not exist with given id : " + authorId));
        return AuthorMapper.mapToAuthorDto(author);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        Author author= authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author does not exist with given id : " + authorId));
        authorRepository.deleteById(authorId);

    }

    public List<AuthorDto> getAuthorsByBookId(Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new ResourceNotFoundException("Book does not exist with given id : " + bookId);
        }

        return authorRepository.findAuthorsByBookId(bookId).stream()
                .map(AuthorMapper::mapToAuthorDto).toList();
    }
}
