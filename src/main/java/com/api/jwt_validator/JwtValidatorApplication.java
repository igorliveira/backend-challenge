package com.api.jwt_validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JwtValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtValidatorApplication.class, args);

	}	
}
