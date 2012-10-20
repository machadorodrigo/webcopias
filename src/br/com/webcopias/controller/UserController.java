package br.com.webcopias.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Boolean isNewAdmin,isNewBoss,isNewTeacher,isNewOperator,isActive;
	private Collection<GrantedAuthority> roleList;
	private User loggedUser;
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

	public Boolean getIsActive() {
		if(this.getSelectedUser() == null) return false;
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Boolean getIsNewAdmin() {
		return isNewAdmin;
	}

	public void setIsNewAdmin(Boolean isNewAdmin) {
		this.isNewAdmin = isNewAdmin;
	}

	public Boolean getIsNewBoss() {
		return isNewBoss;
	}

	public void setIsNewBoss(Boolean isNewBoss) {
		this.isNewBoss = isNewBoss;
	}

	public Boolean getIsNewTeacher() {
		return isNewTeacher;
	}

	public void setIsNewTeacher(Boolean isNewTeacher) {
		this.isNewTeacher = isNewTeacher;
	}

	public Boolean getIsNewOperator() {
		return isNewOperator;
	}

	public void setIsNewOperator(Boolean isNewOperator) {
		this.isNewOperator = isNewOperator;
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
		
		for(Role role : this.selectedUser.getRole()){
			if(role.getName().equals("ROLE_ADM")){
				this.setIsNewAdmin(true);
			}else if(role.getName().equals("ROLE_BOSS")){
				this.setIsNewBoss(true);
			}else if(role.getName().equals("ROLE_TEACHER")){
				this.setIsNewTeacher(true);
			}else if(role.getName().equals("ROLE_OPERATOR")){
				this.setIsNewOperator(true);
			}
		}
		
		if(this.selectedUser.getActive()){
			this.isActive = true;
		}
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

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setIsBoss(Boolean isBoss) {
		this.isBoss = isBoss;
	}

	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

	public void setIsOperator(Boolean isOperator) {
		this.isOperator = isOperator;
	}
	
	public Boolean getIsAdmin(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_ADM")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean getIsBoss(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_BOSS")){
				return true;
			}
		}
		return false;
	}
	
	public Boolean getIsTeacher(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_TEACHER")){
				return true;
			}
		}
		return false;
	}

	
	public Boolean getIsOperator(){
		for(GrantedAuthority auth : this.getRoleList()){
			if(auth.equals("ROLE_OPERATOR")){
				return true;
			}
		}
		return false;
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

	public void prepareCreateUser(ActionEvent actionEvent){
		this.selectedUser = new User();
		this.isNewAdmin = false;
		this.isNewBoss = false;
		this.isNewTeacher = false;
		this.isNewOperator = false;
	}
	
	/**
	 * Atualiza as informações do usuário (O próprio usuário - Editar Conta)
	 * @param actionEvent
	 */
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
			if(this.getSelectedUser().getRegistration().equals(this.getLoggedUser().getRegistration())){
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Não é possível remover seu próprio usuário.");
			}else{
				userImpl.remove(this.getSelectedUser());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário foi removido.");
			}
		}catch(RuntimeException e){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao tentar remover o usuário.");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Cria um novo usuário
	 * @param actionEvent
	 */
	public void createNewUser(ActionEvent actionEvent){
		Set<Role> roles = new HashSet<Role>();
		Role role;
		UserImpl userImpl = new UserImpl();
		FacesMessage msg = null;
		
		if(this.getIsNewAdmin()){
			role = new Role();
			role.setDescription("Administrador");
			role.setName("ROLE_ADM");
			
			roles.add(role);
		}
		if(this.getIsNewBoss()){
			role = new Role();
			role.setDescription("Chefe de Departamento");
			role.setName("ROLE_BOSS");
			
			roles.add(role);
		}
		if(this.getIsNewOperator()){
			role = new Role();
			role.setDescription("Operador");
			role.setName("ROLE_OPERATOR");
			
			roles.add(role);
		}
		if(this.getIsNewTeacher()){
			role = new Role();
			role.setDescription("Professor");
			role.setName("ROLE_TEACHER");
			
			roles.add(role);
		}
		this.getSelectedUser().setActive(this.getIsActive());
		this.getSelectedUser().setRole(roles);
		
		/*
		 * Validações 
		 */
		
		if(this.getSelectedUser().getRegistration() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar a matrícula do usuário.");
		}else if(this.getSelectedUser().getName() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome do usuário.");
		}else if(this.getSelectedUser().getPassword() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar a senha do usuário.");
		}else if(this.getSelectedUser().getCopyLimit() == null){
			this.getSelectedUser().setCopyLimit(0);
		}else if(this.getSelectedUser().getRole().size() == 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário selecionar ao menos um grupo.");
		}else if(userImpl.getUser(this.getSelectedUser().getRegistration()) != null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Este usuário já existe.");
		}else{
			this.getSelectedUser().setRegistrationDate(new Date());
			
			try{
				userImpl.save(this.getSelectedUser());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuário " + this.getSelectedUser().getName() + " criado.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao criar o usuário.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Atualiza usuario
	 * @param actionEvent
	 */
	public void updateUser(ActionEvent actionEvent){
		Set<Role> roles = new HashSet<Role>();
		Role role;
		UserImpl userImpl = new UserImpl();
		FacesMessage msg = null;

		if(this.getIsNewAdmin()){
			role = new Role();
			role.setDescription("Administrador");
			role.setName("ROLE_ADM");
			
			roles.add(role);
		}
		if(this.getIsNewBoss()){
			role = new Role();
			role.setDescription("Chefe de Departamento");
			role.setName("ROLE_BOSS");
			
			roles.add(role);
		}
		if(this.getIsNewOperator()){
			role = new Role();
			role.setDescription("Operador");
			role.setName("ROLE_OPERATOR");
			
			roles.add(role);
		}
		if(this.getIsNewTeacher()){
			role = new Role();
			role.setDescription("Professor");
			role.setName("ROLE_TEACHER");
			
			roles.add(role);
		}
		this.getSelectedUser().setActive(this.getIsActive());
		this.getSelectedUser().setRole(roles);
		
		/*
		 * Validações 
		 */
		
		if(this.getSelectedUser().getRegistration() == null || this.getSelectedUser().getRegistration().trim() == ""){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar a matrícula do usuário.");
		}else if(this.getSelectedUser().getName() == null || this.getSelectedUser().getName().trim() == ""){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome do usuário.");
		}else if(this.getSelectedUser().getPassword() == null || this.getSelectedUser().getPassword().trim() == ""){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar a senha do usuário.");
		}else if(this.getSelectedUser().getCopyLimit() == null){
			this.getSelectedUser().setCopyLimit(0);
		}else if(this.getSelectedUser().getRole().size() == 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário selecionar ao menos um grupo.");
		}else{
			this.getSelectedUser().setRegistrationDate(new Date());
			
			try{
				userImpl.update(this.getSelectedUser());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuário " + this.getSelectedUser().getName() + " atualizado.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao atualizar o usuário.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
