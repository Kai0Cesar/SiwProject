package it.uniroma3.siw.centri.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import it.uniroma3.siw.centri.model.Allievo;

@Component
public class AllievoValidator {

	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "richiesto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "richiesto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "richiesto");

	}

	public boolean supports(Class<?> clazz) {
		return Allievo.class.equals(clazz);
	}

}
