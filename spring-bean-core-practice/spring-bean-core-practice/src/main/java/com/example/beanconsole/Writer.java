package com.example.beanconsole;

import org.springframework.stereotype.Component;

@Component
public class Writer {

    private final Author author;
    private final Book book;

    public Writer(Author author, Book book) {
        System.out.println("🧠 Writer Bean created with dependencies injected!");
        this.author = author;
        this.book = book;
    }

    public void write() {
        author.describe();
        book.details();
        System.out.println("📝 Writing complete using injected beans!");
    }
}
