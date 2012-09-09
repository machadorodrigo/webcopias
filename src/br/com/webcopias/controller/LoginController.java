package br.com.webcopias.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.model.User;

@ManagedBean(name="login")
@SessionScoped
public class LoginController {
	private User registration;
	
	public LoginController(){
		registration = new User();
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				registration.setRegistration(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
			}
		}
	}

	public User getRegistration() {
		return registration;
	}

	public void setRegistration(User registration) {
		this.registration = registration;
	}
	
}
