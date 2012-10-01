package br.com.webcopias.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.Role;
import br.com.webcopias.model.User;

@ManagedBean(name="user")
@SessionScoped
public class UserController {

	private String currentUser;
	private Boolean isAdmin,isBoss,isTeacher,isOperator;
	private Collection<GrantedAuthority> roleList;
	private User loggedUser;

	public UserController(){
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				Collection<GrantedAuthority> role = authentication.getAuthorities();
				this.roleList = role;
				
				//Inicializa setters
				currentUser = ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
				UserImpl userImpl = new UserImpl();
				User user = userImpl.getUser(currentUser);
				this.setLoggedUser(user);
			}
		}
	}


	public Collection<GrantedAuthority> getRoleList() {
		return roleList;
	}

	public void setRoleList(Collection<GrantedAuthority> roleList) {
		this.roleList = roleList;
	}

	public Boolean getIsAdmin(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_ADM")) return true;
		}
		return false;
	}

	public Boolean getIsBoss(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_BOSS")) return true;
		}
		return false;
	}

	public Boolean getIsTeacher(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_TEACHER")) return true;
		}
		return false;
	}

	public Boolean getIsOperator(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_OPERATOR")) return true;
		}
		return false;
	}

	public void selfUpdateUser(ActionEvent actionEvent){
		FacesMessage msg = null;
		
		if(this.getLoggedUser().getName() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "O nome não foi informado.");
		}else if(this.getLoggedUser().getPassword() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "A senha não foi informada.");
		}else if(!(this.getLoggedUser().getRegistration() == null)){
			User user = this.getLoggedUser();
			
			UserImpl userImpl = new UserImpl();
			userImpl.update(user);

			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seus dados foram atualizados.");
		}else if(this.getLoggedUser().getRegistration() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um problema ao buscar suas informações.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg); 
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
}
