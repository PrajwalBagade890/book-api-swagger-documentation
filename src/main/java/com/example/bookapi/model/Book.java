package com.example.bookapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Book information returned by the Book API")
public class Book {
    @Schema(description = "Unique book ID", example = "1")
    private Long id;
    @Schema(description = "Book title", example = "Clean Code")
    private String title;
    @Schema(description = "Book author", example = "Robert C. Martin")
    private String author;
    @Schema(description = "Book price", example = "499.00")
    private double price;

    public Book() {}
    public Book(Long id, String title, String author, double price) {
        this.id = id; this.title = title; this.author = author; this.price = price;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
