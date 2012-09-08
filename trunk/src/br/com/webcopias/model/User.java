package br.com.webcopias.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.Group;

@Entity
@Table(name="USUARIO")
public class User {
	private String registration,name,email,password,photo;
	private Date registrationDate;
	private Boolean active;
	private List<Group> group;
	private List<Discipline> discipline;
	private Department department;
	private Integer copyLimit;
	
	public User(){};
	
	public User(String registration, String name, String email, Date registrationDate,
			Boolean active,List<Group> group,List<Discipline> discipline,
			Department department, String photo, Integer limit) {
		super();
		this.registration = registration;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
		this.active = active;
		this.group = group;
		this.discipline = discipline;
		this.department = department;
		this.photo = photo;
		this.copyLimit = limit;
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

	@Column(columnDefinition = "BOOLEAN")
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
	
	@ManyToMany
	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	@ManyToMany
	public List<Discipline> getDiscipline() {
		return discipline;
	}

	public void setDiscipline(List<Discipline> discipline) {
		this.discipline = discipline;
	}
	
	@OneToOne
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getLimit() {
		return copyLimit;
	}

	public void setLimit(Integer limit) {
		this.copyLimit = limit;
	}
	
}
