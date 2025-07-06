package com.example.beanconsole;

import com.example.beanconsole.Writer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        System.out.println("🚀 Spring Context Initialized");

        Writer writer = context.getBean(Writer.class);
        writer.write();

        context.close();
    }
}
