package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMPRESSAO_USUARIO")
public class UserRequest {
	private User user;
	private CentralCopy central;
	private Document document;
	private String serviceName;
	private int requestId;
	private double serviceCost;
	private boolean active;
	
	public UserRequest(){}
	
	public UserRequest(User user, CentralCopy central, int requestId, Document document,
			String serviceName, boolean active, double serviceCost) {
		super();
		this.user = user;
		this.central = central;
		this.requestId = requestId;
		this.document = document;
		this.serviceName = serviceName;
		this.serviceCost = serviceCost;
		this.active = active;
	}

	@Id
	@Column(unique=true)
	@GeneratedValue
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public CentralCopy getCentral() {
		return central;
	}
	
	public void setCentral(CentralCopy central) {
		this.central = central;
	}
	
	@Column(columnDefinition = "BOOLEAN")	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToOne
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}
}
