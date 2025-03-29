package com.ruphine.bookstore.dto;

import com.ruphine.bookstore.enums.BookGenre;
import lombok.*;

import java.time.LocalDate;


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
}
