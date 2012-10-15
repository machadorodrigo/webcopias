package br.com.webcopias.controller;

import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
	@SuppressWarnings("unused")
	private Boolean isAdmin,isBoss,isTeacher,isOperator,isSelectedAdmin,isSelectedBoss,isSelectedTeacher,isSelectedOperator;
	private Collection<GrantedAuthority> roleList;
	private User loggedUser;
	private DataModel<User> listUsers;
	private User selectedUser;

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

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
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

		if(this.getLoggedUser().getName() == null || this.getLoggedUser().getName().equalsIgnoreCase("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "O nome não foi informado.");
		}else if(this.getLoggedUser().getPassword() == null || this.getLoggedUser().getPassword().equalsIgnoreCase("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "A senha não foi informada.");
		}else if(this.getLoggedUser().getRegistration() == null || this.getLoggedUser().getRegistration().equalsIgnoreCase("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um problema ao buscar suas informações.");
		}else{
			User user = this.getLoggedUser();
			
			UserImpl userImpl = new UserImpl();
			userImpl.update(user);
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seus dados foram atualizados.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg); 
	}
	
	public DataModel<User> getListUsers(){
		
		UserImpl userImpl = new UserImpl();
		List<User> users = userImpl.getUsersList();
		DataModel<User> model = new ListDataModel<User>(users);
		
		return model;
	}

	public void deleteUser(ActionEvent acEvent){
		UserImpl userImpl = new UserImpl();
		FacesMessage msg = null;
		
		try{
			userImpl.remove(this.getSelectedUser());
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário foi removido.");
		}catch(RuntimeException e){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao tentar remover o usuário.");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Boolean getIsSelectedAdmin() {
		User user = this.getSelectedUser();

		if(user != null){
			for(Role role : user.getRole()){
				if(role.getName().equalsIgnoreCase("ROLE_ADM")) return true;
			}
		}
		
		return false;
	}

	public Boolean getIsSelectedBoss() {
		User user = this.getSelectedUser();
		
		if(user != null){
			for(Role role : user.getRole()){
				if(role.getName().equalsIgnoreCase("ROLE_BOSS")) return true;
			}
		}
		
		return false;
	}

	public Boolean getIsSelectedTeacher() {
		User user = this.getSelectedUser();
		
		if(user != null){
			for(Role role : user.getRole()){
				if(role.getName().equalsIgnoreCase("ROLE_TEACHER")) return true;
			}
		}
		
		return false;
	}

	public Boolean getIsSelectedOperator() {
		User user = this.getSelectedUser();
		
		if(user != null){
			for(Role role : user.getRole()){
				if(role.getName().equalsIgnoreCase("ROLE_OPERATOR")) return true;
			}
		}
		
		return false;
	}
}
