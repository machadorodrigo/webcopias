package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CENTRAL_COPIAS")
public class CentralCopy {

	private Integer id,quantityCopy,serviceType,discipline;
	private String userRegistration,document,observation;
	private Boolean active;
	
	public CentralCopy(){};
	
	public CentralCopy(Integer id, Integer quantityCopy, Integer serviceType,
			Integer discipline, String userRegistration, String document,
			String observation, Boolean active) {
		super();
		this.id = id;
		this.quantityCopy = quantityCopy;
		this.serviceType = serviceType;
		this.discipline = discipline;
		this.userRegistration = userRegistration;
		this.document = document;
		this.observation = observation;
		this.active = active;
	}

	
	@Id
	@GeneratedValue
	@Column(unique=true)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getQuantityCopy() {
		return quantityCopy;
	}
	
	public void setQuantityCopy(Integer quantityCopy) {
		this.quantityCopy = quantityCopy;
	}
	
	public String getUserRegistration() {
		return userRegistration;
	}
	
	public void setUserRegistration(String userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	public Integer getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
	@Column(columnDefinition = "BOOLEAN")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Integer discipline) {
		this.discipline = discipline;
	}
}
