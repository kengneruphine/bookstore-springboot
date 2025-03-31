package com.ruphine.bookstore.mapper;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.entities.Book;

public class BookMapper {
    public static BookDto mapToBookDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getGenre(), book.getPublishDate(), book.getAuthors());
    }

    public static Book mapToBookEntity(BookDto bookDto){
        return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getPublishDate(),bookDto.getGenre(), bookDto.getAuthors());
    }
}
