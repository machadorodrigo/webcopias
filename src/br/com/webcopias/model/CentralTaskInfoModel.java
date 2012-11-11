package br.com.webcopias.model;

public class CentralTaskInfoModel {
	private User user;
	private Document document;
	private CentralCopy centralCopy;
	
	public CentralTaskInfoModel(User user, Document document, CentralCopy centralCopy) {
		super();
		this.user = user;
		this.document = document;
		this.centralCopy = centralCopy;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public CentralCopy getCentralCopy() {
		return centralCopy;
	}
	public void setCentralCopy(CentralCopy centralCopy) {
		this.centralCopy = centralCopy;
	}
}
