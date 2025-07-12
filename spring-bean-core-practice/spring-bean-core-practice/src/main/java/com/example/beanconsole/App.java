package com.example.beanconsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class App {


    private static  final Logger log=LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        log.info("🔍 Listing all beans in the container:");
        String[] allBeanNames = context.getBeanDefinitionNames();
        Arrays.stream(allBeanNames).map(beanName -> "➡️ " + beanName).forEach(System.out::println);
        MathOperations mathOperations= (int a, int b)->a+b;
      var sum=  mathOperations.add(12,13);
      System.out.println("sum of 12 and 13 is :" +sum);

      Animal animal=new Animal("Lion", 10);
     System.out.println(animal);

    }
}


interface  MathOperations{
 int  add(int a, int b);
}
 record Animal(String name,int age){}