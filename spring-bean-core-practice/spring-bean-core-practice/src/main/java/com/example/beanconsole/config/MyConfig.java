package com.example.beanconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Book myBook() {
        System.out.println("📘 Book Bean created (Manual via @Bean)");
        return new Book("The Art of Spring", "Shafiqul Islam");
    }
}
