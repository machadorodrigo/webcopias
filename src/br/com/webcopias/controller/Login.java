package br.com.webcopias.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	private String user,password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void checkLogin(){
		if(user == null){
			FacesMessage msg = new FacesMessage("Erro. ", "Usuário não informado");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else if(password == null){
			FacesMessage msg = new FacesMessage("Erro. ", "Usuário não informado");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
