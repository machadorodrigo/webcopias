package br.com.webcopias.model;

import javax.persistence.Column;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="DISCIPLINA")
public class Discipline {
	private Integer copyLimit;
	private String disciplineCode,disciplineName;
	
	public Discipline(){};
	
	public Discipline(String disciplineCode, String disciplineName, Integer copyLimit) {
		super();
		this.disciplineCode = disciplineCode;
		this.disciplineName = disciplineName;
		this.copyLimit = copyLimit;
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
}
