package com.example.beanconsole.config;

import com.example.beanconsole.MarketToSellBook;
import com.example.beanconsole.book.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MyConfig {

    @Bean
    public Book myBook() {
        System.out.println("📘 Book Bean created (Manual via @Bean)");
        return new Book("Salmon","Shafiqul Islam ");
    }
    @Bean
    public MarketToSellBook setMarketBeantToIoCContainer(){
        return  new MarketToSellBook();
    }

}
