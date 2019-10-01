package br.com.elements.accountRegistration.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String email;
	
	private String password;
	
	public Long getId() {
		return id;
	}
	
	public UserSS() {
	}
	
	public UserSS(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
