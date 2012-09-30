package br.com.webcopias.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class User {
	private String registration,name,email,userPassword,photo;
	private Date registrationDate;
	private Boolean active;
	private Integer copyLimit;
	private List<Role> role;
	
	public User(){};
	
	public User(String registration, String name, String email, Date registrationDate,
			Boolean active,String password, String photo, Integer copyLimit, List<Role> role) {
		super();
		this.registration = registration;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
		this.active = active;
		this.userPassword = password;
		this.photo = photo;
		this.copyLimit = copyLimit;
		this.role = role;
	}
	
	@Id
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

	@Column(columnDefinition = "BOOLEAN")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getCopyLimit() {
		return copyLimit;
	}

	public void setCopyLimit(Integer copyLimit) {
		this.copyLimit = copyLimit;
	}

	@ManyToMany
	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
	
}
