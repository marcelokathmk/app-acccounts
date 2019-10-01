package br.com.elements.accountRegistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.elements.accountRegistration.dto.AccountDto;
import br.com.elements.accountRegistration.errors.ResultErrorHandler;
import br.com.elements.accountRegistration.service.AccountServiceImpl;

@Controller
public class AccountController extends BaseController{

	@Autowired
	private ResultErrorHandler resultErrorHandler;
	
	@Autowired
	private AccountServiceImpl service;
	
	@GetMapping("/login")
    public ModelAndView login(String success) {
		ModelAndView mv = new ModelAndView("login");
		addSuccess(success, mv);
		return mv;
    }

	@GetMapping("/home")
    public ModelAndView home(AccountDto account, String errors, String success) {
        return defaultUrl(account, errors, success);
    }
	
	@GetMapping("/")
    public ModelAndView defaultUrl(AccountDto account, String errors, String success) {
		ModelAndView mv = new ModelAndView("home");
		
		addErrors(errors, mv);
		addSuccess(success, mv);
		setUserInContext(account, mv);
		
		return mv;
    }
	
	private void setUserInContext(AccountDto account, ModelAndView mv) {
		if	(account != null && !StringUtils.isEmpty(account.getName())) {
			mv.addObject("account", account);
		}else {
			if	(getUserAuthenticated() != null) {
				AccountDto accountDto = service.getAccountInformations(getUserAuthenticated().getId());
				if	(accountDto != null) {
					mv.addObject("account", accountDto);
				}
			}
		}
	}

	@GetMapping("/register")
	public ModelAndView register() {
		return register(new AccountDto(), null);
	}
	
	private ModelAndView register(AccountDto account, String errors) {
		ModelAndView mv = new ModelAndView("register");
		addErrors(errors, mv);
		mv.addObject("account", account);
		
		return mv;
	}

	@PostMapping("/createAccount")
	public ModelAndView createAccount(@Valid AccountDto account, BindingResult result) {
		if(result.hasErrors()) {
			return register(account, resultErrorHandler.getResultErrors(result));
		}
		
		try {
			service.saveAccount(account);
		} catch (Exception e) {
			return register(account, e.getMessage());
		}
		
		return login("Account created successfully!");
	}
	
	@PostMapping("/updateAccount")
	public ModelAndView updateAccount(@Valid AccountDto account, BindingResult result) {
		if(result.hasErrors()) {
			return home(account, resultErrorHandler.getResultErrors(result), null);
		}
		
		try {
			service.saveAccount(account);
		} catch (Exception e) {
			return home(account, e.getMessage(), null);
		}
		
		return home(account, null, "Account updated successfully!");
	}
}

