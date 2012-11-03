package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CENTRAL_COPIAS")
public class CentralCopy {

	private Integer id,quantityCopy,document;
	private String userRegistration,observation,discipline,serviceName;
	private Boolean active;
	private Double serviceCost;
	
	public CentralCopy(){};
	
	public CentralCopy(Integer id, Integer quantityCopy, String serviceName,
			String discipline, String userRegistration, Integer document,
			String observation, Boolean active, Double serviceCost) {
		
		super();
		
		this.id = id;
		this.quantityCopy = quantityCopy;
		this.serviceName = serviceName;
		this.discipline = discipline;
		this.userRegistration = userRegistration;
		this.document = document;
		this.observation = observation;
		this.active = active;
		this.serviceCost = serviceCost;
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
	
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getDocument() {
		return document;
	}

	public void setDocument(Integer document) {
		this.document = document;
	}
	
	@Column(columnDefinition = "BOOLEAN")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}
}
