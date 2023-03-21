package com.example.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SearchApiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApiServerApplication.class, args);
    }
}
