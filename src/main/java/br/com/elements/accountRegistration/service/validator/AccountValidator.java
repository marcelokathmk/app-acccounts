package br.com.elements.accountRegistration.service.validator;

import org.springframework.stereotype.Component;

import br.com.elements.accountRegistration.dto.AccountDto;
import br.com.elements.accountRegistration.service.exception.PasswordException;

@Component
public class AccountValidator {

	public void validate(AccountDto accountDto) {
		validatePassword(accountDto.getPassword(), accountDto.getConfirmPassword());
	}
	
	private void validatePassword(String password, String confirmPassword) {
		if	(!password.toUpperCase().equals(confirmPassword.toUpperCase())) {
			throw new PasswordException("Passwords entered do not match");
		}
	}
}
