package br.com.elements.accountRegistration.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.elements.accountRegistration.converter.AccountConverter;
import br.com.elements.accountRegistration.dto.AccountDto;
import br.com.elements.accountRegistration.model.Account;
import br.com.elements.accountRegistration.repository.AccountRepository;
import br.com.elements.accountRegistration.service.exception.EmailException;
import br.com.elements.accountRegistration.service.validator.AccountValidator;

@Service
public class AccountServiceImpl {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private AccountValidator validator;
	
	@Autowired
	private AccountConverter converter;

	@Transactional
	public void saveAccount(AccountDto accountDTO) {
		validator.validate(accountDTO);
		
		try {
			Account account = converter.getAccountEntityFromDto(accountDTO);
			repository.save(account);
		} catch (DataIntegrityViolationException div) {
			throw new EmailException("The email you entered already exists");
		} catch (Exception e) {
			throw e;
		}	
	}
	
	public AccountDto getAccountInformations(Long id) {
		Account account = repository.findById(id).orElse(null);
		if	(account != null) {
			return converter.getAccountDtoFromEntity(account);
		}
		return null;
	}
}
