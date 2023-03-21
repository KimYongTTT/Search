package com.example.search.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SearchBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchBatchApplication.class, args);
    }
}
