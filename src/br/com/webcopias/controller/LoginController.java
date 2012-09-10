package br.com.webcopias.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.User;

@ManagedBean(name="login")
@SessionScoped
public class LoginController {
	private User registration;
	
	public LoginController(){
		UserImpl userimpl = new UserImpl();
		registration = new User();
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				
				if(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername() == null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Usu�rio n�o foi informado."));
				}
				if(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getPassword() == null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Senha n�o foi informada."));
				}
				
				System.out.println(">>>>>" + ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).toString());
				registration.setRegistration(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
				User usr = userimpl.getUser(registration.getRegistration());
				registration.setName(usr.getName());
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
