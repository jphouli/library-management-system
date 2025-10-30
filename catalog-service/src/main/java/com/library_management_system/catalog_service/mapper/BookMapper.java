package com.library_management_system.catalog_service.mapper;

import com.library_management_system.catalog_service.dto.BookRequestDTO;
import com.library_management_system.catalog_service.dto.BookResponseDTO;
import com.library_management_system.catalog_service.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookResponseDTO bookToResponse(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();

        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setPublisher(book.getPublisher());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setGenre(book.getGenre());

        return bookResponseDTO;
    }

    public Book bookRequestToBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();

        book.setAuthor(bookRequestDTO.getAuthor());
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublisher(bookRequestDTO.getPublisher());

        return book;
    }

    public Book bookUpdateRequestToExistingBook(Book book, BookRequestDTO bookRequestDTO) {
        book.setPublisher(bookRequestDTO.getPublisher());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setAuthor(bookRequestDTO.getAuthor());

        return book;
    }
}
