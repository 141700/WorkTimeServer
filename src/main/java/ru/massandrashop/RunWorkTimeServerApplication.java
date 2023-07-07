package ru.massandrashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableCaching
@Configuration
@SpringBootApplication
public class RunWorkTimeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunWorkTimeServerApplication.class, args);
    }

}