package com.booking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author vijaysingh
 */
@EnableRetry
@SpringBootApplication
public class BookmyhikingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmyhikingApplication.class, args);
	}

}
