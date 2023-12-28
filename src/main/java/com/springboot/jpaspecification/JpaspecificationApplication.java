package com.springboot.jpaspecification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the JpaSpecification application.
 * This class contains the main method to start the Spring Boot application.
 * The application uses Spring Data JPA Specifications for querying and filtering data.
 *
 * <p>The {@code @SpringBootApplication} annotation is used to enable various Spring Boot features,
 * including auto-configuration, component scanning, and the setup of the Spring application context.
 * It combines the functionality of {@code @Configuration}, {@code @EnableAutoConfiguration},
 * and {@code @ComponentScan} annotations.</p>
 */
@SpringBootApplication
public class JpaspecificationApplication {

	/**
	 * Main method to launch the JpaSpecification application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(JpaspecificationApplication.class, args);
	}

}
