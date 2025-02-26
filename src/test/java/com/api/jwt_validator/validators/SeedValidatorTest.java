package com.api.jwt_validator.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class SeedValidatorTest {

	@InjectMocks
	private SeedValidator seedValidator;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void validSeedTest() {
		String seed = "2";
		assertTrue(seedValidator.validate(seed));			
	}
	
	@Test
	void invalidSeedTest() {
		String seed = "20";
		assertFalse(seedValidator.validate(seed));			
	}	

}
