package com.api.jwt_validator.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class RoleValidatorTest {

	@InjectMocks
	private RoleValidator roleValidator;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void validRoleTest() {
		Set<String> validRoles = Set.of("Admin", "Member", "External");
		for (String role : validRoles) {
			assertTrue(roleValidator.validate(role));			
		}
	}	
	
	@Test
	void invalidRoleTest() {
		String role = "Seed";
		assertFalse(roleValidator.validate(role));			
	}
	
}
