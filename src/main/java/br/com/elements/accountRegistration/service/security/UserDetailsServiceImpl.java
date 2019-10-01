package br.com.elements.accountRegistration.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.elements.accountRegistration.config.UserSS;
import br.com.elements.accountRegistration.model.Account;
import br.com.elements.accountRegistration.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email);
		if	(account == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(account.getId(), account.getEmail(), account.getPassword());
	}

}
