package br.com.webcopias.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.DualListModel;

import br.com.webcopias.dao.DisciplineImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.User;

@ManagedBean(name="discipline")
@SessionScoped
public class DisciplineController {
	private Discipline discipline;
	private DualListModel<User> users;
	private List<User> userToList;
	
	public DisciplineController(){
		UserImpl userImpl = new UserImpl();
		
		List<User> source = userImpl.getUsersList();
		List<User> target = new ArrayList<User>();
		System.out.println("Construtor " + this.getDiscipline());
		this.users = (this.getDiscipline() == null)? new DualListModel<User>(source,target):getDisciplinesDualList();
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	public List<User> getUserToList() {
		List<User> userToList = (this.getDiscipline() == null)?new ArrayList<User>():new ArrayList<User>(this.getDiscipline().getRegistration());
		return userToList;
	}
	
	public void prepareCreateDiscipline(ActionEvent actionEvent){
		this.discipline = new Discipline();
		
		UserImpl userImpl = new UserImpl();
		List<User> source = userImpl.getUsersList();
		List<User> target = new ArrayList<User>();
		
		this.users = new DualListModel<User>(source, target);
		
	}
	
	public DualListModel<User> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<User> users) {
		this.users = users;
	}
	
	public DataModel<Discipline> getListDisciplines(){
		
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		List<Discipline> discipline = disciplineImpl.getDisciplinesList();
		DataModel<Discipline> model = new ListDataModel<Discipline>(discipline);
		
		return model;
	}
	
	public void deleteDiscipline(ActionEvent acEvent){
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		FacesMessage msg = null;
		
		try{
			disciplineImpl.remove(this.getDiscipline());
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "A disciplina foi removida.");
		}catch(RuntimeException e){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao tentar remover a disciplina.");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Cria uma nova disciplina
	 * @param actionEvent
	 */
	public void createNewDiscipline(ActionEvent actionEvent){
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		FacesMessage msg = null;
		
		/*
		 * Validações 
		 */
		if(this.getDiscipline().getDisciplineCode() == null || this.getDiscipline().getDisciplineCode().equals("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o código da disciplina.");
		}else if(this.getDiscipline().getDisciplineName() == null || this.getDiscipline().getDisciplineName().equals("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome da disciplina.");
		}else if(this.getDiscipline().getCopyLimit() == null || this.getDiscipline().getCopyLimit().equals("")){
			this.getDiscipline().setCopyLimit(0);
		}else{
			Set<User> usersTarget = new HashSet<User>(this.getUsers().getTarget());
			this.getDiscipline().setRegistration(usersTarget);
			
			try{
				disciplineImpl.save(this.getDiscipline());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Disciplina " + this.getDiscipline().getDisciplineName() + " criada.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao criar a disciplina.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Atualiza disciplina
	 * @param actionEvent
	 */
	public void updateDiscipline(ActionEvent actionEvent){
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		FacesMessage msg = null;
		
		/*
		 * Validações 
		 */
		
		if(this.getDiscipline().getDisciplineCode() == null || this.getDiscipline().getDisciplineCode() == ""){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o código da disciplina.");
		}else if(this.getDiscipline().getDisciplineName() == null || this.getDiscipline().getDisciplineName() == ""){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome da disciplina.");
		}else if(this.getDiscipline().getCopyLimit() == null || this.getDiscipline().getCopyLimit().equals("")){
			this.getDiscipline().setCopyLimit(0);
		}else{
			Set<User> userSet = new HashSet<User>(this.getUsers().getTarget());
			this.getDiscipline().setRegistration(userSet);
			try{
				disciplineImpl.update(this.getDiscipline());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Disciplina " + this.getDiscipline().getDisciplineName() + " atualizada.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao atualizar a disciplina.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public DualListModel<User> getDisciplinesDualList(){
		UserImpl userImpl = new UserImpl();
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		
		List<User> temp = new ArrayList<User>();
		List<User> usersList = userImpl.getUsersList();
		
		if(this.getDiscipline() == null) return new DualListModel<User>(usersList, temp);
		
		Discipline disciplines = disciplineImpl.getDiscipline(this.getDiscipline().getDisciplineCode());
		List<User> setToListUser = new ArrayList<User>(disciplines.getRegistration());
		
		List<String> usrArray = new ArrayList<String>();
		List<String> userArray = new ArrayList<String>();
		
		for(int i=0;i<usersList.size();i++){
			usrArray.add(usersList.get(i).getRegistration());
		}
		
		for(int i=0;i<setToListUser.size();i++){
			userArray.add(setToListUser.get(i).getRegistration());
		}

		for(int i=0;i<usrArray.size();i++){
			if(userArray.contains(usrArray.get(i))) usersList.remove(i);
		}
		return new DualListModel<User>(usersList, setToListUser);
	}
	
}
