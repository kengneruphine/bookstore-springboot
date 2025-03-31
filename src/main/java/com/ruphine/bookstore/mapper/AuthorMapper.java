package com.ruphine.bookstore.mapper;

import com.ruphine.bookstore.dto.AuthorDto;
import com.ruphine.bookstore.entities.Author;

public class AuthorMapper {

    public static AuthorDto mapToAuthorDto(Author author){
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(), author.getEmail(), author.getBooks());
    }

    public static Author mapToAuthorEntity(AuthorDto authorDto){
        return new Author(authorDto.getId(), authorDto.getFirstName(), authorDto.getLastName(), authorDto.getEmail(), authorDto.getBooks());
    }
}
