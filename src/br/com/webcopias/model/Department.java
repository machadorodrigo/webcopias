package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTAMENTO")
public class Department {
	private String departamentCode,departmentName;
	
	public Department(){};
	
	public Department(String departamentCode, String departmentName) {
		super();
		this.departamentCode = departamentCode;
		this.departmentName = departmentName;
	}

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

}
