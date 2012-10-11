package br.com.webcopias.service;

import java.util.Date;
import java.util.List;

import br.com.webcopias.dao.RoleImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.Role;
import br.com.webcopias.model.User;

public class CreateUser {
	private User user;
	
	public CreateUser(String registration, String name, String email, String password, String copyLimit, String role){
		user = new User();
		
		System.out.println("Matrícula: " + registration);
		System.out.println("Nome: " + name);
		System.out.println("E-mail: " + email);
		System.out.println("Senha: " + password);
		System.out.println("Limite de Cópias: " + copyLimit);
		System.out.println("Papel: " + role);
		
		this.user.setRegistration(registration);
		this.user.setName(name);
		this.user.setEmail(email);
		this.user.setPassword(password);
		this.user.setRegistrationDate(new Date());
		this.user.setActive(true);
		this.user.setPhoto(null);
		if(copyLimit.equals("") || copyLimit == null){
			this.user.setCopyLimit(0);
		}else{
			try {
				this.user.setCopyLimit(Integer.parseInt(copyLimit));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				this.user.setCopyLimit(0);
			}
		}
		this.user.setRole((role==null)?null:getRolesList(role));
	}
	
	/**
	 * Cria o usuário
	 * @return String Retorna ok caso não ocorra problema ou retorna o erro
	 */
	public String createNewUser(){
		UserImpl userImpl = new UserImpl();
		
		if(this.user.getRegistration() == null || this.user.getRegistration().equals("")){
			return "É necessário informar a matrícula";
		}else if(this.user.getName() == null || this.user.getName().equals("")){
			return "É necessário informar o nome";
		}else if(this.user.getPassword() == null || this.user.getPassword().equals("")){
			return "É necessário informar uma senha";
		}else if(userImpl.getUser(this.user.getRegistration()) != null){
			return "O usuário já existe";
		}
		
		try{
			userImpl.save(this.user);
		}catch(RuntimeException e){
			return e.getMessage();
		}
		
		return "ok";		
	}
	
	/**
	 * Recebe os papéis separados por vírgula e joga em uma string com os papés entre ''
	 * Exemplo: Se receber ROLE_ADM,ROLE_BOSS então ficará 'ROLE_ADM','ROLE_BOSS'
	 * @param role Papéis separados por vírgula
	 * @return List Retorna uma lista de Role
	 */
	public List<Role> getRolesList(String role){
		String[] roleSplit = role.split(",");
		RoleImpl roleImpl = new RoleImpl();
		String roleSrt = "";
		
		if(roleSplit.length == 1){
			roleSrt = "'"+role+"'";
		}else{
			for(int i=0;i<roleSplit.length;i++){
				if(i==roleSplit.length-1){
					roleSrt += "'"+roleSplit[i]+"'";
				}else{
					roleSrt += "'"+roleSplit[i]+"',";
				}
			}
		}
		
		return roleImpl.getRolesByList(roleSrt);
	}
}
