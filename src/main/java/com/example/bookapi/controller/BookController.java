package com.example.bookapi.controller;

import com.example.bookapi.dto.BookRequest;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Operations for creating, reading, updating and deleting books")
@SecurityRequirement(name = "ApiKeyAuth")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) { this.bookService = bookService; }

    @GetMapping
    @Operation(summary = "Get all books", description = "Returns the complete list of books.")
    @ApiResponse(responseCode = "200", description = "Books returned successfully")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Finds and returns one book using its ID.")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to retrieve", example = "1", required = true)
            @PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return book == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(book);
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Adds a new book and returns the created record.")
    @ApiResponse(responseCode = "201", description = "Book created",
            content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "400", description = "Invalid book details")
    public ResponseEntity<Book> createBook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Book details to create", required = true)
            @Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Updates the book matching the supplied ID.")
    @ApiResponse(responseCode = "200", description = "Book updated")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to update", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated book details", required = true)
            @Valid @RequestBody BookRequest request) {
        Book updated = bookService.updateBook(id, request);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes the book matching the supplied ID.")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete", example = "1", required = true)
            @PathVariable Long id) {
        return bookService.deleteBook(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
