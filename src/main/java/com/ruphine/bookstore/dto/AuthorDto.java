package com.ruphine.bookstore.dto;

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

    private Set<Book> books;
}
