package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BaijiajiehoutaiApplication //extends SpringBootServletInitializer 
{
	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(BaijiajiehoutaiApplication.class);
//    
//	}
	public static void main(String[] args) {
		SpringApplication.run(BaijiajiehoutaiApplication.class, args);
	}
}
