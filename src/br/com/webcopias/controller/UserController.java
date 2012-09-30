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

	private String registration,name,email,userPassword,photo;
	private Date registrationDate;
	private Boolean active,isAdmin,isBoss,isTeacher,isOperator;
	private Integer copyLimit;
	private List<Role> role;
	private Collection<GrantedAuthority> roleList;
	private User user;
	
	public UserController(){
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				Collection<GrantedAuthority> role = authentication.getAuthorities();
				
				this.roleList = role;
			}
		}
	}
	
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCopyLimit() {
		return copyLimit;
	}

	public void setCopyLimit(Integer copyLimit) {
		this.copyLimit = copyLimit;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
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
	
	public void selfShowUser(ActionEvent actionEvent){
		String currentUser = null;
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				currentUser = ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
				
				UserImpl userimpl = new UserImpl();
				
				user = userimpl.getUser(currentUser);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + currentUser);
				
				this.setRegistration(user.getRegistration());
				this.setName(user.getName());
				this.setUserPassword(user.getPassword());
				this.setEmail(user.getEmail());
				this.setActive(user.getActive());
				this.setCopyLimit(user.getCopyLimit());
				this.setPhoto(user.getPhoto());
				this.setRegistrationDate(user.getRegistrationDate());
				this.setRole(user.getRole());
			}
		}
	}
	
	public void selfUpdateUser(ActionEvent actionEvent){
		user = new User();
		FacesMessage msg = null;

		if(this.name.equals(null)){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "O nome não foi informado.");
		}else if(this.userPassword.equals(null)){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "A senha não foi informada.");
		}else if(!this.registration.equals(null)){
			user.setRegistration(this.getRegistration());
			user.setName(this.getName());
			user.setActive(this.getActive());
			user.setPhoto(this.getPhoto());
			user.setPassword(this.getUserPassword());
			user.setCopyLimit(this.getCopyLimit());
			user.setEmail(this.getEmail());
			user.setRegistrationDate(this.getRegistrationDate());
			user.setRole(this.getRole());
			
			UserImpl userImpl = new UserImpl();
			userImpl.update(user);

			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seus dados foram atualizados.");
		}else if(this.registration.equals(null)){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um problema ao buscar suas informações.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg); 
	}
}
