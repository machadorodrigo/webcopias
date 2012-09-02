package br.com.webcopias.model;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="USUARIO")
public class User {
	private String registration,name,email,password;
	private Date registrationDate;
	private Boolean active;
	
	public User(){};
	
	public User(String registration, String name, String email, Date registrationDate, Boolean active) {
		super();
		this.registration = registration;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
		this.active = active;
	}
	
	@Column(unique=true)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
