package com.springboot.jpaspecification.config;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Configuration} class named {@link FakerConfig} for providing a Spring bean for Faker, a library for generating fake data.
 * This class defines a Spring bean named "faker" that can be injected into other Spring components.
 */
@Configuration
public class FakerConfig {

    /**
     * Creates and configures a bean for Faker.
     *
     * @return A new instance of the Faker class, which can be used to generate fake data.
     */
    @Bean
    public Faker faker() {
        return new Faker();
    }

}
