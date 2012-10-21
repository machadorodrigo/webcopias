package br.com.webcopias.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="DISCIPLINA")
public class Discipline {
	private Integer copyLimit;
	private String disciplineCode,disciplineName;
	private Set<User> registration;
	
	public Discipline(){};
	
	public Discipline(String disciplineCode, String disciplineName, Integer copyLimit, Set<User> registration) {
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<User> getRegistration() {
		return registration;
	}

	public void setRegistration(Set<User> registration) {
		this.registration = registration;
	}
}
