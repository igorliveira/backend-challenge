package com.api.jwt_validator.validators;

import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameValidator {

	public boolean validate(String name) {

		if (name.isEmpty() && name.isBlank()) {
			log.error("Claim Name não possui valor");
			return false;			
		}

		if (!Pattern.compile("^\\D*$").matcher(name).matches()) {
			log.error("Abrindo o JWT, a Claim Name possui caracter de numeros");
			return false;	
		} else if (!Pattern.compile("^.{0,256}$").matcher(name).matches()) {
			log.error("Abrindo o JWT, a Claim Name possui mais de 256 caracteres");
			return false;
		}
		
		return true;
	}
}
