package com.ruphine.bookstore.entities;

import com.ruphine.bookstore.enums.BookGenre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public Book(String title, BookGenre genre, LocalDate publishDate) {
        this.title = title;
        this.genre= genre;
        this.publishDate=publishDate;
    }

    @ManyToMany(fetch= FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinTable(
            name="book_author",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id")
    )
    private Set<Author> authors = new HashSet<>();;


}
