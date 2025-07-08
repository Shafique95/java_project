package com.example.beanconsole.writer;

import com.example.beanconsole.author.Author;
import com.example.beanconsole.book.Book;
import org.springframework.stereotype.Component;

@Component
public class Writer {

    private Author author;
    private Book book;

    public Writer(Author author, Book book) {
        System.out.println("🧠 Writer Bean created with dependencies injected!");
        this.author = author;
        this.book = book;
//        author.describe();
//        this.callmeAgain();
//        this.callmeAgain();

    }

    public void write() {
        author.describe();
        book.details();
        System.out.println("📝 Writing complete using injected beans!");
    }
    public void callmeAgain(){
        System.out.println("call me again Dear");
    }

}
