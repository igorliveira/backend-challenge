package com.api.jwt_validator.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Type;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ClaimValidatorTest {

	@InjectMocks
	private ClaimValidator claimValidator;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	public Map<String, String> createMap(String jwtDecoded) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		return gson.fromJson(jwtDecoded, type);
	}
	
	@Test
	void validClaimTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\",\"Name\":\"Maria Olivia\"}";
		assertTrue(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test 
	void invalidClaimExceedTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\",\"Name\":\"Maria Olivia\",\"Org\":\"BR\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test 
	void invalidClaimWithoutDefaultClaimsTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test 
	void invalidClaimWithouDefaultClaimsAndAdditionalClaimTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\",\"Org\":\"BR\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test
	void invalidClaimNameWithNumberTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\",\"Name\":\"M4ria Olivia\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test
	void invalidClaimNameCharacterLimitTest() {
		String jwtDecoded = "{\"Role\":\"External\",\"Seed\":\"88037\",\"Name\":\"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuaa\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test
	void invalidRoleInexistsRoleTest() {
		String jwtDecoded = "{\"Role\":\"Ext\",\"Seed\":\"88037\",\"Name\":\"Maria Olivia\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	@Test
	void invalidSeedPrimeNumberTest() {
		String jwtDecoded = "{\"Role\":\"Ext\",\"Seed\":\"2\",\"Name\":\"Maria Olivia\"}";
		assertFalse(claimValidator.validate(createMap(jwtDecoded)));
	}
	
	
	
	
	
	
	
	
	
	

}
