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
	private Integer id;
	private String userRegistration,document,serviceType,quantityCopy;
	private Double totalValue;
	private Date dateFinalized;
	
	public CentralHistory(){};
	
	public CentralHistory(Integer id, String userRegistration,	String document, String serviceType,
			String quantityCopy, Double totalValue, Date dateFinalized) {
		super();
		this.id = id;
		this.userRegistration = userRegistration;
		this.document = document;
		this.serviceType = serviceType;
		this.quantityCopy = quantityCopy;
		this.totalValue = totalValue;
		this.dateFinalized = dateFinalized;
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
	
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getQuantityCopy() {
		return quantityCopy;
	}
	
	public void setQuantityCopy(String quantityCopy) {
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
}
