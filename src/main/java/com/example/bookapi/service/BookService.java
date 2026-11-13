package com.example.bookapi.service;

import com.example.bookapi.dto.BookRequest;
import com.example.bookapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(2);

    public BookService() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin", 499.00));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch", 599.00));
    }

    public List<Book> getAllBooks() {
        return books.values().stream().sorted(Comparator.comparing(Book::getId)).toList();
    }
    public Book getBookById(Long id) { return books.get(id); }
    public Book addBook(BookRequest request) {
        long id = idCounter.incrementAndGet();
        Book book = new Book(id, request.getTitle(), request.getAuthor(), request.getPrice());
        books.put(id, book);
        return book;
    }
    public Book updateBook(Long id, BookRequest request) {
        if (!books.containsKey(id)) return null;
        Book book = new Book(id, request.getTitle(), request.getAuthor(), request.getPrice());
        books.put(id, book);
        return book;
    }
    public boolean deleteBook(Long id) { return books.remove(id) != null; }
}
