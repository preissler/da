package com.da.persistance.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = {"com.da.persistance.common.repository"})
@EntityScan(basePackages = {"com.da.persistance.common.model.db"})
@ComponentScan(basePackages = {"com.da.persistance.common.transformer", "com.da.persistance.common.validator"})
@EnableAutoConfiguration
public class ProjectCommonsTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectCommonsTestApplication.class, args);
    }
}
