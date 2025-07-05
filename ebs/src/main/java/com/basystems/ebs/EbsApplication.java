package com.basystems.ebs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbsApplication.class, args);
		System.err.println("eddd");
		int x=10;
		int y =12;
		int z = x + y;
		  System.out.println(z);
	}

}
