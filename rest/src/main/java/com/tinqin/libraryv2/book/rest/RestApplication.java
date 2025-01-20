package com.tinqin.libraryv2.book.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = {
        "com.tinqin.libraryv2.book" })
@EntityScan("com.tinqin.libraryv2.book.persistence.models")
@EnableJpaRepositories(basePackages = "com.tinqin.libraryv2.book.persistence.repositories")
@EnableFeignClients(basePackages = "com.tinqin.libraryv2.book.domain")
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
