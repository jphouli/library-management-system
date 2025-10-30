package com.library_management_system.catalog_service.controller;

import com.library_management_system.catalog_service.dto.BookRequestDTO;
import com.library_management_system.catalog_service.dto.BookResponseDTO;
import com.library_management_system.catalog_service.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(catalogService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable UUID id) {
        return ResponseEntity.ok(catalogService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@RequestParam(name="author") String author) {
        return ResponseEntity.ok(catalogService.getAllBooksByAuthor(author));
    }

    @GetMapping
    public ResponseEntity<BookResponseDTO> getBookByTitle(@RequestParam(name="title") String title) {
        return ResponseEntity.ok(catalogService.getBookByTitle(title));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(catalogService.createBook(bookRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable UUID id, @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(catalogService.updateBook(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        catalogService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookByTitle(@RequestParam(name="title") String title) {
        catalogService.deleteBookByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
