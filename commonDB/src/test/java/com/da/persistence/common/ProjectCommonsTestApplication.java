package com.da.persistence.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = {"com.da.persistence.common.repository"})
@EntityScan(basePackages = {"com.da.persistence.common.model.db"})
@ComponentScan(basePackages = {"com.da.persistence.common.transformer", "com.da.persistence.common.validator"})
@EnableAutoConfiguration
public class ProjectCommonsTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectCommonsTestApplication.class, args);
    }
}
