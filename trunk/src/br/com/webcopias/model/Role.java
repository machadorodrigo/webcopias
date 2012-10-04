package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAPEL")
public class Role {
	private String name,description;
	
	public Role(){}
	
	public Role(String name,String description) {
		super();
		this.name = name;
		this.setDescription(description);
	}
	
	@Id
	@Column(nullable=false,unique=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
