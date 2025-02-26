package com.api.jwt_validator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Boolean> validate(@RequestBody String jwtToken) {
    	if (jwtToken != null) {
    		boolean validation = jwtValidatorService.validateJwt(jwtToken); 
    		if (validation) {
    			log.info("Abrindo o JWT, as informações contidas atendem a descrição");
    		}
    		return ResponseEntity.ok(validation);
    	}
    	log.error("Token não pode ser nulo");
    	return ResponseEntity.ok(false);
    }
    
    @PostMapping("/jwtvalidate/{jwt}")
    public ResponseEntity<Boolean> validateURL(@PathVariable String jwt) {
    	if (jwt != null) {
    		boolean validation = jwtValidatorService.validateJwt(jwt); 
    		if (validation) {
    			log.info("Abrindo o JWT, as informações contidas atendem a descrição");
    		}
    		return ResponseEntity.ok(validation);
    	}
    	log.error("Token não pode ser nulo");
    	return ResponseEntity.ok(false);
    }
}