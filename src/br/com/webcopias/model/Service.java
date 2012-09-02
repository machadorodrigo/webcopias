package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="SERVICO")
public class Service {
	private Integer id;
	private String serviceName;
	private Double serviceCost;
	
	public Service(){};
	
	public Service(Integer id, String serviceName, Double serviceCost) {
		super();
		this.id = id;
		this.serviceName = serviceName;
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
