package br.com.webcopias.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="HISTORICO_CENTRAL")
public class CentralHistory {
	private Integer id;
	private CentralCopy userRegistration,document,serviceType,quantityCopy;
	private Double totalValue;
	private Date dateFinalized;
	
	public CentralHistory(){};
	
	public CentralHistory(Integer id, CentralCopy userRegistration,	CentralCopy document, CentralCopy serviceType,
			CentralCopy quantityCopy, Double totalValue, Date dateFinalized) {
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
	
	public CentralCopy getUserRegistration() {
		return userRegistration;
	}
	
	public void setUserRegistration(CentralCopy userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	public CentralCopy getDocument() {
		return document;
	}
	
	public void setDocument(CentralCopy document) {
		this.document = document;
	}
	
	public CentralCopy getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(CentralCopy serviceType) {
		this.serviceType = serviceType;
	}
	
	public CentralCopy getQuantityCopy() {
		return quantityCopy;
	}
	
	public void setQuantityCopy(CentralCopy quantityCopy) {
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
