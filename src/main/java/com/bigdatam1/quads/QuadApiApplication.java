package com.bigdatam1.quads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.cloud.spring.data.datastore.repository.config.EnableDatastoreRepositories;

@SpringBootApplication
@EnableDatastoreRepositories
public class QuadApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuadApiApplication.class, args);
    }
}