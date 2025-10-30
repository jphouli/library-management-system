package com.library_management_system.catalog_service.service;

import com.library_management_system.catalog_service.dto.BookRequestDTO;
import com.library_management_system.catalog_service.dto.BookResponseDTO;
import com.library_management_system.catalog_service.exception.BookNotFoundException;
import com.library_management_system.catalog_service.mapper.BookMapper;
import com.library_management_system.catalog_service.model.Book;
import com.library_management_system.catalog_service.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final BookMapper bookMapper;
    public CatalogService(CatalogRepository catalogRepository, BookMapper bookMapper) {
        this.catalogRepository = catalogRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookResponseDTO> getAllBooks() {
        return catalogRepository.findAll().stream().map(bookMapper::bookToResponse).toList();
    }

    public List<BookResponseDTO> getBooksByTitle(String title) {
        return catalogRepository.findByTitleContainingIgnoreCase(title).stream().map(bookMapper::bookToResponse).toList();
    }

    public List<BookResponseDTO> getAllBooksByAuthor(String author) {
        return catalogRepository.findByAuthorContainingIgnoreCase(author).stream().map(bookMapper::bookToResponse).toList();
    }

    public BookResponseDTO getBookById(UUID id) {
        return bookMapper.bookToResponse(
                catalogRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id)));
    }

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        return bookMapper.bookToResponse(catalogRepository.save(bookMapper.bookRequestToBook(bookRequestDTO)));
    }

    public BookResponseDTO updateBook(UUID id, BookRequestDTO bookRequestDTO) {
        Book existingBook = catalogRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book not found with ID: " + id));

        return bookMapper.bookToResponse(
                catalogRepository.save(bookMapper.bookUpdateRequestToExistingBook(existingBook, bookRequestDTO)));
    }

    public void deleteBookById(UUID id) {
        catalogRepository.deleteById(id);
    }
}
