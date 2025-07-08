package com.example.beanconsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        System.out.println("🔍 Listing all beans in the container:");
        String[] allBeanNames = context.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println("➡️ " + beanName);
        }
        context.close();

    }
}
