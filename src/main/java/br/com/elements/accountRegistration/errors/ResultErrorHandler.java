package br.com.elements.accountRegistration.errors;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Component
public class ResultErrorHandler {

	public String getResultErrors(BindingResult result) {
		StringBuilder errors = new StringBuilder();
		for	(ObjectError error: result.getAllErrors()){
			errors.append("- ");
			errors.append(error.getDefaultMessage());
			errors.append("\n");
		}
		return errors.toString();
	}
}
