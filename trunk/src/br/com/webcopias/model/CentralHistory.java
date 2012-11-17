package br.com.webcopias.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HISTORICO_CENTRAL")
public class CentralHistory {
	private Integer id,document,serviceType,quantityCopy;
	private String userRegistration;
	private Double totalValue;
	private Date dateFinalized,dateCreation;
	
	public CentralHistory(){};
	
	public CentralHistory(Integer id, String userRegistration,	Integer document, Integer serviceType,
			Integer quantityCopy, Double totalValue, Date dateFinalized, Date dateCreation) {
		super();
		this.id = id;
		this.userRegistration = userRegistration;
		this.document = document;
		this.serviceType = serviceType;
		this.quantityCopy = quantityCopy;
		this.totalValue = totalValue;
		this.dateFinalized = dateFinalized;
		this.dateCreation = dateCreation;
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
	
	public String getUserRegistration() {
		return userRegistration;
	}
	
	public void setUserRegistration(String userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	public Integer getDocument() {
		return document;
	}
	
	public void setDocument(Integer document) {
		this.document = document;
	}
	
	public Integer getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	public Integer getQuantityCopy() {
		return quantityCopy;
	}
	
	public void setQuantityCopy(Integer quantityCopy) {
		this.quantityCopy = quantityCopy;
	}
	
	public Double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	
	public Date getDateFinalized() {
		return dateFinalized;
	}
	
	public void setDateFinalized(Date dateFinalized) {
		this.dateFinalized = dateFinalized;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
}
