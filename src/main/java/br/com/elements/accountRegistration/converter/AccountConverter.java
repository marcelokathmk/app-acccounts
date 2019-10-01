package br.com.elements.accountRegistration.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.elements.accountRegistration.dto.AccountDto;
import br.com.elements.accountRegistration.model.Account;

@Component
public class AccountConverter {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Account getAccountEntityFromDto(AccountDto accountDto) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);
		account.setPassword(encoder.encode(account.getPassword()));
		account.setTelephone(account.getTelephone().replaceAll("[^0-9]*", ""));
		return account;
	}
	
	public AccountDto getAccountDtoFromEntity(Account account) {
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(account, accountDto);
		return accountDto;
	}
}
