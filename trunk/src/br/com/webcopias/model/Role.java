package br.com.webcopias.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAPEL")
public class Role {
	private String name;
	
	public Role(String name) {
		super();
		this.name = name;
	}
	
	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
