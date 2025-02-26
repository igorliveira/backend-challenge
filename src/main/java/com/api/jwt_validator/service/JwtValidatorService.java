package com.api.jwt_validator.service;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.api.jwt_validator.validators.ClaimValidator;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtValidatorService {

	public boolean validateJwt(String jwt) {
		String jwtDecoded = this.decodeJwt(jwt);
		log.info(jwtDecoded);
		if (!isValidJson(jwtDecoded)) {
			log.error("JWT invalido");
			return false;
		}
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> map = gson.fromJson(jwtDecoded, type);			
		return new ClaimValidator().validate(map);			
	}

	private String decodeJwt(String jwt) {
		String jwtDecoded;
		try {
			jwtDecoded = new String(Base64.getDecoder().decode(jwt.split("\\.")[1]));
			if (jwtDecoded.isEmpty() && jwtDecoded.isBlank()) {
				return "{\"Role\":\"\",\"Seed\":\"\",\"Name\":\"\"}";
			}
			return jwtDecoded;
		} catch (ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException(
            		String.format("Index out of bounds: %s chars", jwt.length())
            		);
		}
	}
	
	private boolean isValidJson(String json) {
        try {
        	JsonParser.parseString(json);
        } catch (JsonSyntaxException ex) {
            return false;
        }
        return true;
    }
}
