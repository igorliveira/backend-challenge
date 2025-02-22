package com.api.jwt_validator.validators;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeedValidator {
	public boolean validate(String seed) {

		if (seed.isEmpty() && seed.isBlank()) {
			log.error("Claim Seed nao possui valor");
			return false;
		}
		
		Integer primeNumber = Integer.parseInt(seed);
		
		if (primeNumber == 2 || primeNumber == 3) {
			 return  true; 
		 }
		
	     if (primeNumber < 2 || primeNumber % 2 == 0 || primeNumber % 3 == 0) {
	    	 log.error("Abrindo o JWT, a Claim Seed nao e um numero primo");
	    	 return false;
	     }
	     
	     for (int i = 5; i * i <= primeNumber; i += 2) {
	    	 if (primeNumber % i == 0) {
	    		 log.error("Abrindo o JWT, a Claim Seed nao e um numero primo");
	    		 return false;
	    	 }
	     }
	     
	     return true;
	}
}

