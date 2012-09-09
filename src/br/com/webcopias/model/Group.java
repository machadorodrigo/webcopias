package br.com.webcopias.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="GRUPO")
public class Group {
	private String groupCode,groupName;
	private List<User> registration;

	public Group(){};
	
	public Group(String groupCode, String groupName,List<User> registration) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.setRegistration(registration);
	}
	
	@Id
	@Column(unique=true)
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public List<User> getRegistration() {
		return registration;
	}

	public void setRegistration(List<User> registration) {
		this.registration = registration;
	}
	
}
