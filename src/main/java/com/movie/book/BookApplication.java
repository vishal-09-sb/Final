package com.movie.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BookApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
