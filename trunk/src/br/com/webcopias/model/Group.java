package br.com.webcopias.model;

import javax.persistence.Column;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="GRUPO")
public class Group {
	private String groupCode,groupName;

	public Group(){};
	
	public Group(String groupCode, String groupName) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
	}
	
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
}
