package br.com.webcopias.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.webcopias.dao.CentralCopyImpl;
import br.com.webcopias.dao.DocumentImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.CentralTaskInfoModel;
import br.com.webcopias.model.Document;
import br.com.webcopias.model.User;

@ManagedBean(name="viewWork")
@SessionScoped
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
		
		//Prepara arquivo para download
		String fullPath = document.getDocumentPath()+"\\"+document.getId()+"\\"+document.getDocumentName()+"."+document.getDocumentType();
		System.out.println(fullPath);
		InputStream stream = null;
		
		try {
			stream = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(prepareContentType(document.getDocumentType()) + " - " + document.getDocumentName()+"."+document.getDocumentType());
		this.file = new DefaultStreamedContent(stream, prepareContentType(document.getDocumentType()), (document.getDocumentName()+"."+document.getDocumentType())); 
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

	private String prepareContentType(String docType){
		String contentType = null;
		
		if(docType.equals("jpg")){
			contentType = "image/jpg";
		}else if(docType.equals("jpeg")){
			contentType = "image/jpg";
		}else if(docType.equals("png")){
			contentType = "image/png";
		}else if(docType.equals("pdf")){
			contentType = "application/pdf";
		}else if(docType.equals("doc")){
			contentType = "application/msword";
		}else if(docType.equals("docx")){
			contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}else if(docType.equals("xls")){
			contentType = "application/vnd.ms-excel";
		}else if(docType.equals("xlsx")){
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}else{
			contentType = "text";
		}
		return contentType;
	}
}
