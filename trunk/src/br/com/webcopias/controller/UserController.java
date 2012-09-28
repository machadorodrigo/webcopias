package br.com.webcopias.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.model.Role;

@ManagedBean(name="user")
@SessionScoped
public class UserController {

	private String registration,name,email,userPassword,photo;
	private Date registrationDate;
	private Boolean active,isAdmin,isBoss,isTeacher,isOperator;
	private Integer copyLimit;
	private List<Role> role;
	private Collection<GrantedAuthority> roleList;
	
	public UserController(){
		this.name= "Eu";
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
	
}
