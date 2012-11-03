package br.com.webcopias.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.webcopias.dao.DisciplineImpl;
import br.com.webcopias.dao.ParameterImpl;
import br.com.webcopias.dao.ServiceImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.dao.UserRequestImpl;
import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.Service;
import br.com.webcopias.model.User;
import br.com.webcopias.model.UserRequest;

import org.primefaces.model.UploadedFile;

@ManagedBean(name="work")
@SessionScoped
public class NewWorkController {
	private List<Service> serviceList;
	private List<UserRequest> userRequestList;
	private int selectedService, qtdCopy;
	private UserRequest selectedRequest;
	private User currentUser;
	private HashMap<String, Integer> serviceMap;
	private String comment,documentDescription;
	private UploadedFile file;
	
	@SuppressWarnings("unused")
	private boolean belongsDiscipline,hasService,hasParameter;
	
	public NewWorkController(){
		UserRequestImpl userRequestImpl = new UserRequestImpl();
		UserImpl userImpl = new UserImpl();
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				User user = userImpl.getUser(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
				this.setCurrentUser(user);
			}
		}
		
		this.serviceMap = new HashMap<String, Integer>();
		this.selectedRequest = new UserRequest();
		this.userRequestList = userRequestImpl.getRequestByUser(this.getCurrentUser());
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public UserRequest getSelectedRequest() {
		return selectedRequest;
	}

	public void setSelectedRequest(UserRequest selectedRequest) {
		this.selectedRequest = selectedRequest;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<UserRequest> getUserRequestList() {
		return userRequestList;
	}

	public void setUserRequestList(List<UserRequest> userRequestList) {
		this.userRequestList = userRequestList;
	}
	
	public void prepareCreateWork(ActionEvent actionEvent) {
		
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public int getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(int selectedService) {
		this.selectedService = selectedService;
	}
	
	public int getQtdCopy() {
		return qtdCopy;
	}

	public void setQtdCopy(int qtdCopy) {
		this.qtdCopy = qtdCopy;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public boolean getBelongsDiscipline(){
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		List<Discipline> listDiscipline = disciplineImpl.getDisciplinesList();
		
		for(Discipline d : listDiscipline){
			for(User u : d.getRegistration()){
				if(u.getRegistration().equals(this.getCurrentUser().getRegistration())){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean getHasService(){
		ServiceImpl serviceImpl = new ServiceImpl();
		return (serviceImpl.getServicesList().size() > 0);
	}
	
	public HashMap<String, Integer> getServiceMap(){
		ServiceImpl serviceImpl = new ServiceImpl();
		List<Service> lService = serviceImpl.getServicesList();
		
		for(Service s : lService){
			this.serviceMap.put(s.getServiceName(), s.getId());
		}
		
		return this.serviceMap;
	}
	
	public boolean getHasParameter(){
		ParameterImpl parameterImpl = new ParameterImpl();
		return (parameterImpl.getParametersList().size() > 0);
	}
	
	public void addWork(){
		if(this.file != null){
			
			FacesMessage msg = new FacesMessage("Sucesso! ", this.file.getFileName() + " foi enviado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			try {
				copyFile(this.file.getFileName(), this.file.getInputstream(),0);
			} catch (IOException e) {
				System.out.println("Erro: " + e);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro.", "Nenhum arquivo foi selecionado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String copyFile(String fileName, InputStream in, int docId) {
		ParameterImpl parameterImpl = new ParameterImpl();
		String path = parameterImpl.getParametersList().get(0).getVolume()+"/WebCopias/document/"+docId+"/";
		
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(path+fileName));

			int read = 0;
			byte[] bytes = new byte[5120];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return path;
	}

}
















