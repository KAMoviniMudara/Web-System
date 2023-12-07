package com.example.Web.System;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = {"com.example.Web.System.entity"})
public class WebSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebSystemApplication.class, args);
		Logger logger = LoggerFactory.getLogger(WebSystemApplication.class);
		logger.info("Hello, SLF4J!");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
