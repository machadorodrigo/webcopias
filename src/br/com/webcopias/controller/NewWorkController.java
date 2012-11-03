package br.com.webcopias.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.UploadedFile;
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
	private UploadedFile selectedFile;
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

	public UploadedFile getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(UploadedFile selectedFile) {
		this.selectedFile = selectedFile;
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
	
	public void addWork(ActionEvent actionEvent){
		if(this.selectedFile != null){
			System.out.println( "=========== " + this.selectedFile.getFileName());
			System.out.println( "=========== " + this.selectedFile.getSize());
		}
	}
	
	public void copyFile(String fileName, InputStream in) {
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(""));

			int read = 0;
			byte[] bytes = new byte[5120];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println("Pehhhh, erro: " + e);
		}
	}

}
















