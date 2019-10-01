package br.com.elements.accountRegistration.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import br.com.elements.accountRegistration.config.UserSS;
import br.com.elements.accountRegistration.service.security.UserService;

public class BaseController {

	protected void addErrors(String errors, ModelAndView mv) {
		if	(!StringUtils.isEmpty(errors)) {
			mv.addObject("errors", errors);
		}
	}
	
	protected void addSuccess(String success, ModelAndView mv) {
		if	(!StringUtils.isEmpty(success)) {
			mv.addObject("success", success);
		}
	}
	
	protected UserSS getUserAuthenticated() {
		return UserService.getUserAuthenticated();
	}
}
