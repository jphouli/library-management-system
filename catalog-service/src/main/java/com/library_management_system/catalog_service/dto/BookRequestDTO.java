package com.library_management_system.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;
    @NotBlank(message = "Author cannot be blank")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    private String author;
    @NotBlank(message = "Publisher cannot be blank")
    @Size(min = 2, max = 100, message = "Publisher name must be between 2 and 100 characters")
    private String publisher;
    @NotBlank(message = "Genre cannot be blank")
    @Size(min = 3, max = 50, message = "Genre must be between 3 and 50 characters")
    private String genre;
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
