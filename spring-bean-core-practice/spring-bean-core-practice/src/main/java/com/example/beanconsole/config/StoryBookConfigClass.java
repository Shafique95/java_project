package com.example.beanconsole.config;

import com.example.beanconsole.book.StoryBook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoryBookConfigClass {
    // This method will create a StoryBook bean and register it in the Spring IoC container
    @Bean
    StoryBook storyBook(){
        System.out.println("📚 StoryBook Bean going to creating  (Manual via @Bean)");
        return new StoryBook();
    }
}
