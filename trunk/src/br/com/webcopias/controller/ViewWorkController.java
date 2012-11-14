package br.com.webcopias.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.com.webcopias.dao.CentralCopyImpl;
import br.com.webcopias.dao.DocumentImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.CentralTaskInfoModel;
import br.com.webcopias.model.Document;
import br.com.webcopias.model.User;

@ManagedBean(name="viewWork")
@RequestScoped
public class ViewWorkController {

	private int workId;
	private StreamedContent file;
	private CentralTaskInfoModel centralTaskInfoModel;
	
	public ViewWorkController(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.workId = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("taskid"));
		
		CentralCopy centralCopy = null;
		User user = null;
		Document document = null;
		
		CentralCopyImpl centralCopyImpl = new CentralCopyImpl();
		UserImpl userImpl = new UserImpl();
		DocumentImpl documentImpl = new DocumentImpl();
		
		centralCopy = centralCopyImpl.getCentralCopy(this.workId);
		user = userImpl.getUser(centralCopy.getUserRegistration());
		document = documentImpl.getDocument(centralCopy.getDocument());
		
		this.centralTaskInfoModel = new CentralTaskInfoModel(user, document, centralCopy);
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public CentralTaskInfoModel getCentralTaskInfoModel() {
		return centralTaskInfoModel;
	}

	public void setCentralTaskInfoModel(CentralTaskInfoModel centralTaskInfoModel) {
		this.centralTaskInfoModel = centralTaskInfoModel;
	}

	public StreamedContent getFile() {
		return file;
	}
}
