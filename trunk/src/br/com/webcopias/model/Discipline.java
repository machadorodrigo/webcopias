package br.com.webcopias.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="DISCIPLINA")
public class Discipline {
	private Integer copyLimit;
	private String disciplineCode,disciplineName;
	private List<User> registration;
	
	public Discipline(){};
	
	public Discipline(String disciplineCode, String disciplineName, Integer copyLimit, List<User> registration) {
		super();
		this.disciplineCode = disciplineCode;
		this.disciplineName = disciplineName;
		this.copyLimit = copyLimit;
		this.registration = registration;
	}
	
	@Id
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
	public List<User> getRegistration() {
		return registration;
	}

	public void setRegistration(List<User> registration) {
		this.registration = registration;
	}
}
