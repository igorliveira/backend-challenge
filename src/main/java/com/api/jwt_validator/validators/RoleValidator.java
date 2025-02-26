package com.api.jwt_validator.validators;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleValidator {
	
	public boolean validate(String role) {
		
		if (role.isEmpty() && role.isBlank()) {
			log.error("Claim Role não possui valor");
			return false;			
		}
		
		Set<String> validRoles = Set.of("Admin", "Member", "External"); 
		
	    if (!validRoles.contains(role)) {
	    	log.error("Abrindo o JWT, a Claim Role não e valida");
	    	return false;
	    }
	    
	    return true;
	}
}
