package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo = "CENTRAL_COPIAS")
public class CentralCopy {
	private Integer id,quantityCopy;
	private User userRegistration;
	private Document document;
	private Service serviceType;
	private String observation;
	private Boolean active;
	
	public CentralCopy(){};
	
	public CentralCopy(Integer id, Integer qualtityCopy, User userRegistration,
			Service serviceType) {
		super();
		this.id = id;
		this.quantityCopy = qualtityCopy;
		this.userRegistration = userRegistration;
		this.serviceType = serviceType;
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
	
	public User getUserRegistration() {
		return userRegistration;
	}
	
	public void setUserRegistration(User userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	public Service getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(Service serviceType) {
		this.serviceType = serviceType;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
