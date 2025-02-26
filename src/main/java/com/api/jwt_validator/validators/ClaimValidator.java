package com.api.jwt_validator.validators;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClaimValidator {

	public boolean validate(Map<String, String> map) {
		return validateKeys(map) && new NameValidator().validate(map.get("Name"))
				&& new SeedValidator().validate(map.get("Seed")) && new RoleValidator().validate(map.get("Role"));
	}

	private boolean validateKeys(Map<String, String> map) {

		Set<String> requiredKeys = Set.of("Name", "Role", "Seed");

		if (map.keySet().equals(requiredKeys)) {
			return true;
		} else if (map.keySet().containsAll(requiredKeys)) {
			Set<String> resultSet = map.keySet();
			resultSet.removeAll(requiredKeys);
			log.error("Abrindo o JWT, foi encontrado mais de 3 claims. Claims adicionais: " + resultSet.toString());
			return false;
		} else {
			Set<String> resultSet1 = new HashSet<>(map.keySet());
			Set<String> resultSet2 = new HashSet<>(requiredKeys);
			resultSet1.removeAll(requiredKeys);			
			resultSet2.removeAll(map.keySet());
			if (!resultSet1.isEmpty() && !resultSet2.isEmpty()) {
				log.error(String.format("Abrindo o JWT, nao foi encontrado os claims obrigatorios: %s. Claims adicional encontrado: %s", resultSet2.toString(),
						 resultSet1.toString()));
							
				return false;
			} else {
				log.error("Abrindo o JWT, nao foi encontrado os claims obrigatorios: "
						+ resultSet2.toString());						
				return false;
			}
		}
	}

}
