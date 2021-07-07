package com.egen.thchome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ThcHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThcHomeApplication.class, args);
    }

}
