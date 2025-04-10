package com.ruphine.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruphine.bookstore.entities.Book;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private Set<Book> books;
}
