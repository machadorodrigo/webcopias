package br.com.webcopias.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
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

import br.com.webcopias.dao.CentralCopyImpl;
import br.com.webcopias.dao.DisciplineImpl;
import br.com.webcopias.dao.DocumentImpl;
import br.com.webcopias.dao.ParameterImpl;
import br.com.webcopias.dao.ServiceImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.dao.UserRequestImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.Document;
import br.com.webcopias.model.Service;
import br.com.webcopias.model.User;
import br.com.webcopias.model.UserRequest;

import org.hibernate.HibernateException;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="work")
@SessionScoped
public class NewWorkController {
	private List<Service> serviceList;
	private List<UserRequest> userRequestList = new ArrayList<UserRequest>();
	private int selectedService, qtdCopy;
	private UserRequest selectedRequest;
	private User currentUser;
	private HashMap<String, Integer> serviceMap;
	private HashMap<String, String> disciplineMap;
	private String comment,documentDescription,selectedDiscipline;
	private UploadedFile file;
	
	@SuppressWarnings("unused")
	private boolean belongsDiscipline,hasService,hasParameter;
	
	public NewWorkController(){
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
		this.disciplineMap = new HashMap<String, String>();
		
		this.selectedRequest = new UserRequest();
		
		this.updateDataGrid();
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

	public String getSelectedDiscipline() {
		return selectedDiscipline;
	}

	public void setSelectedDiscipline(String selectedDiscipline) {
		this.selectedDiscipline = selectedDiscipline;
	}
	
	private void updateDataGrid(){
		UserRequestImpl userRequestImpl = new UserRequestImpl();
		this.userRequestList = userRequestImpl.getRequestByUser(this.getCurrentUser());
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
	
	public HashMap<String, String> getDisciplineMap(){
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		List<Discipline> lDiscipline = disciplineImpl.getDisciplinesList();
		
		for(Discipline d : lDiscipline){
			for(User u : d.getRegistration()){
				if(u.getRegistration().equals(this.getCurrentUser().getRegistration())){
					this.disciplineMap.put(d.getDisciplineName(), d.getDisciplineCode());
				}
			}
		}
		
		return this.disciplineMap;
	}
	
	public boolean getHasParameter(){
		ParameterImpl parameterImpl = new ParameterImpl();
		return (parameterImpl.getParametersList().size() > 0);
	}
	
	public void addWork(){
		if(this.file != null){
			System.out.println(this.file.getFileName());
			
			ParameterImpl parameterImpl = new ParameterImpl();
			
			ServiceImpl serviceImpl = new ServiceImpl();
			Service service = serviceImpl.getService(this.selectedService);
			
			String path = parameterImpl.getParametersList().get(0).getVolume();
			FacesMessage msg = null;
			String docPath = null;
			
			Document doc = this.createDocument(this.file, this.documentDescription, path);
			
			if(doc == null){
				msg = new FacesMessage("Erro! ", "Ocorreu um erro ao adicionar o documento.");
			}else{
				try {
					docPath = copyFile(this.file.getFileName(), this.file.getInputstream(),doc.getId(),path);
				} catch (IOException e) {
					e.printStackTrace();
					msg = new FacesMessage("Erro! ", "Ocorreu um erro ao fazer o upload do documento.");
					this.deleteDocument(doc);
				}
				
				if(docPath != null){
					CentralCopy centralCopy = this.createCentralCopy(doc.getId(), service);
					if(centralCopy != null){
						this.createUserRequest(centralCopy,doc,service);
						msg = new FacesMessage("Sucesso! ", "A tarefa de código "+ centralCopy.getId() +" de impressão foi criada.");
					}else{
						msg = new FacesMessage("Erro! ", "Ocorreu um erro ao criar a impressão.");
					}
				}else{
				}
			}
			
			this.updateDataGrid();
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro.", "Nenhum arquivo foi selecionado.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String copyFile(String fileName, InputStream in, int docId, String volume) {
		
		String absolutePath = volume+"/WebCopias/document/"+docId+"/";
		
		try {
			File file = new File(absolutePath);
			if(!file.exists()) file.mkdir();
			
			OutputStream out = new FileOutputStream(new File(file.getAbsolutePath()+"/"+fileName));

			int read = 0;
			byte[] bytes = new byte[10124];

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
		
		return absolutePath;
	}

	private Document createDocument(UploadedFile file, String description, String path){
		Document document = new Document();
		DocumentImpl documentImpl = new DocumentImpl();
		Document docInfo = new Document();
		
		String[] fileExt = file.getFileName().split("\\.");
		
		document.setDocumentType(fileExt[fileExt.length-1]);
		document.setDocumentName(fileExt[0]);
		document.setDocumentDescription((description == null)?"":description);
		document.setDocumentPath(path+"\\WebCopias\\document");
		document.setDocumentSize(file.getSize());
		
		try{
			docInfo = documentImpl.save(document);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
		return docInfo;
	}
	
	private boolean deleteDocument(Document document){
		DocumentImpl documentImpl = new DocumentImpl();
		
		try{
			documentImpl.remove(document);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private CentralCopy createCentralCopy(int docId, Service service){
		CentralCopyImpl centralCopyImpl = new CentralCopyImpl();
		CentralCopy centralCopy = new CentralCopy();
		
		centralCopy.setActive(true);
		centralCopy.setDiscipline(this.getSelectedDiscipline());
		centralCopy.setDocument(docId);
		centralCopy.setObservation(this.getComment());
		centralCopy.setQuantityCopy(this.qtdCopy);
		centralCopy.setUserRegistration(this.currentUser.getRegistration());
		centralCopy.setServiceName(service.getServiceName());
		centralCopy.setServiceCost(service.getServiceCost());
		
		try{
			centralCopy = centralCopyImpl.save(centralCopy);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
		return centralCopy;
	}

	private void createUserRequest(CentralCopy centralCopy, Document document, Service service){
		UserRequest userRequest = new UserRequest();
		UserRequestImpl userRequestImpl = new UserRequestImpl();
		
		userRequest.setActive(true);
		userRequest.setCentral(centralCopy);
		userRequest.setDocument(document);
		userRequest.setServiceCost(service.getServiceCost());
		userRequest.setServiceName(service.getServiceName());
		userRequest.setUser(this.getCurrentUser());

		try{
			userRequestImpl.save(userRequest);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
}
















