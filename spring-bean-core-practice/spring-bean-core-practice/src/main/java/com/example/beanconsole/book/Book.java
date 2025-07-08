package com.example.beanconsole.book;

public class Book {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void details() {
        System.out.println("📖 Book: " + title + " by " + author);
    }
}
