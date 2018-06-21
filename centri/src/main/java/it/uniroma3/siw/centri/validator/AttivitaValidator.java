package it.uniroma3.siw.centri.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.centri.model.Attivita;

@Component
public class AttivitaValidator implements Validator {
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "Campo invalido");
	}
	
	@Override
	public boolean supports(Class<?> c) {
		return Attivita.class.equals(c);
	}
}
