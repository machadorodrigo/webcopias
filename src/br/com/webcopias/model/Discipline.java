package br.com.webcopias.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.webcopias.model.Department;

@Entity
@Table(name="DISCIPLINA")
public class Discipline {
	private Integer copyLimit;
	private String disciplineCode,disciplineName;
	private List<Department> department;
	
	public Discipline(){};
	
	public Discipline(String disciplineCode, String disciplineName, Integer copyLimit,List<Department> department) {
		super();
		this.disciplineCode = disciplineCode;
		this.disciplineName = disciplineName;
		this.copyLimit = copyLimit;
		this.department = department;
	}
	
	@Column(unique=true)
	public String getDisciplineCode() {
		return disciplineCode;
	}
	public void setDisciplineCode(String disciplineCode) {
		this.disciplineCode = disciplineCode;
	}
	public String getDisciplineName() {
		return disciplineName;
	}
	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}
	public Integer getCopyLimit() {
		return copyLimit;
	}
	public void setCopyLimit(Integer copyLimit) {
		this.copyLimit = copyLimit;
	}
	
	@ManyToMany
	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}
}
