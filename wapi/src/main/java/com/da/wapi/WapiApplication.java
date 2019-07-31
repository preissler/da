package com.da.wapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//TODO add security
@SpringBootApplication
public class WapiApplication {
	private static final Logger log = LoggerFactory.getLogger(WapiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WapiApplication.class, args);
	}

}
