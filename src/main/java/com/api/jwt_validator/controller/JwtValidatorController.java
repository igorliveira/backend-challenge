package com.api.jwt_validator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jwt_validator.service.JwtValidatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class JwtValidatorController {
	
	private final JwtValidatorService jwtValidatorService = new JwtValidatorService();
	
    @PostMapping("/jwtvalidate")
    public ResponseEntity<Boolean> validateJwt(@RequestBody String jwtToken) {
    	if (jwtToken != null) {
    		boolean validation = jwtValidatorService.validateJwt(jwtToken); 
    		return ResponseEntity.ok(validation);
    	}
    	log.error("Token não pode ser nulo");
    	return ResponseEntity.ok(false);
    }
}