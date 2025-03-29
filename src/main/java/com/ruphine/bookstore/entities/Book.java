package com.ruphine.bookstore.entities;

import com.ruphine.bookstore.enums.BookGenre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name="publish_date")
    private LocalDate publishDate;

    private BookGenre genre;



}
