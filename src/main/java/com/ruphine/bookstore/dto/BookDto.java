package com.ruphine.bookstore.dto;

import com.ruphine.bookstore.entities.Author;
import com.ruphine.bookstore.enums.BookGenre;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private Long id;
    private String title;
    private BookGenre genre;
    private LocalDate publishDate;
    private Set<Author> authors;
}
