package br.com.webcopias.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.User;

@ManagedBean(name="login")
@SessionScoped
public class LoginController {
	private User loggedUser;
	private UserImpl userimpl;
	
	public LoginController(){
		userimpl = new UserImpl();
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				User user = userimpl.getUser(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
				this.setLoggedUser(user);
			}
		}
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
}
