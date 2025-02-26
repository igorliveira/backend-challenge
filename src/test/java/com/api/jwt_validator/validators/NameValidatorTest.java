package com.api.jwt_validator.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NameValidatorTest {

	@InjectMocks
	private NameValidator nameValidator;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void validNameTest() {
		String name = "Maria";
		assertTrue(nameValidator.validate(name));
	}
	
	@Test
	void invalidNameWithNumberTest() {
		String name = "M4ria";
		assertFalse(nameValidator.validate(name));
	}
	
	@Test
	void invalidNameWith257CharactersTest() {
		String name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuaa";
		assertFalse(nameValidator.validate(name));
	}
}
