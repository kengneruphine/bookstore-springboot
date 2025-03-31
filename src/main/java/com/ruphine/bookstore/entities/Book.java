package com.ruphine.bookstore.entities;

import com.ruphine.bookstore.enums.BookGenre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name="book_author",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    private List<Author> authorsList;



}
