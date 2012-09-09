package br.com.webcopias.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTAMENTO")
public class Department {
	private String departamentCode,departmentName;
	private User registration;
	private List<Discipline> discipline;
	public Department(){};
	
	public Department(String departamentCode, String departmentName, User registration, List<Discipline> discipline) {
		super();
		this.departamentCode = departamentCode;
		this.departmentName = departmentName;
		this.registration = registration;
		this.setDiscipline(discipline);
	}

	@Id
	@Column(unique=true)
	public String getDepartamentCode() {
		return departamentCode;
	}

	public void setDepartamentCode(String departamentCode) {
		this.departamentCode = departamentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@OneToOne
	public User getRegistration() {
		return registration;
	}

	public void setRegistration(User registration) {
		this.registration = registration;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public List<Discipline> getDiscipline() {
		return discipline;
	}

	public void setDiscipline(List<Discipline> discipline) {
		this.discipline = discipline;
	}

}
