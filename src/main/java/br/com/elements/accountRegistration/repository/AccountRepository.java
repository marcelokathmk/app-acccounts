package br.com.elements.accountRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elements.accountRegistration.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	@Transactional(readOnly = true)
	public Account findByEmail(String email);
}
