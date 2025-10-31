package com.library_management_system.catalog_service.controller;

import com.library_management_system.catalog_service.dto.BookRequestDTO;
import com.library_management_system.catalog_service.dto.BookResponseDTO;
import com.library_management_system.catalog_service.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalogs")
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

    @GetMapping(params = {"author", "!title"})
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@RequestParam(name="author") String author) {
        return ResponseEntity.ok(catalogService.getAllBooksByAuthor(author));
    }

    @GetMapping(params = {"title", "!author"})
    public ResponseEntity<List<BookResponseDTO>> getBooksByTitle(@RequestParam(name="title") String title) {
        return ResponseEntity.ok(catalogService.getBooksByTitle(title));
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
}
