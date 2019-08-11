package com.da.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@SpringBootApplication
@EnableRedisRepositories
@EntityScan(basePackages = {"com.da.persistence.common.model.db"})
public class PersistanceApplication {



	public static void main(String[] args) {
		SpringApplication.run(PersistanceApplication.class, args);
	}

}
