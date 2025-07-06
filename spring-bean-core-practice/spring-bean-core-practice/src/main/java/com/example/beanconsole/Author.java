package com.example.beanconsole;

import org.springframework.stereotype.Component;

@Component
public class Author {
    public Author() {
        System.out.println("✍️ Author Bean created (Auto via @Component)");
    }

    public void describe() {
        System.out.println("✍️ I am the author, and I love writing!");
    }
}
