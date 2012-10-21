package br.com.webcopias.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.DualListModel;

import br.com.webcopias.dao.DepartmentImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.Department;
import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.Role;
import br.com.webcopias.model.User;

@ManagedBean(name="department")
@SessionScoped
public class DepartmentController {
	private Department department;
	private String selectedBoss;
	private HashMap<String, String> heads;
	private DualListModel<Discipline> listDiscipline;

	public DepartmentController(){
		this.department = new Department();
	}

	public String getSelectedBoss() {
		return selectedBoss;
	}

	public void setSelectedBoss(String selectedBoss) {
		this.selectedBoss = selectedBoss;
	}
	
	public void prepareCreateDepartment(ActionEvent actionEvent){
		this.department = new Department();
		this.setSelectedBoss(null);
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public DualListModel<Discipline> getListDiscipline() {
		
		
		return listDiscipline;
	}

	public void setListDiscipline(DualListModel<Discipline> listDiscipline) {
		this.listDiscipline = listDiscipline;
	}
	
	public DataModel<Department> getListDepartments(){
		
		DepartmentImpl departmentImpl = new DepartmentImpl();
		List<Department> department = departmentImpl.getDepartmentsList();
		DataModel<Department> model = new ListDataModel<Department>(department);
		
		return model;
	}
	
	public void deleteDepartment(ActionEvent acEvent){
		DepartmentImpl departmentImpl = new DepartmentImpl();
		FacesMessage msg = null;
		
		try{
			departmentImpl.remove(this.getDepartment());
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O departamento foi removido.");
		}catch(RuntimeException e){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao tentar remover o departamento.");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Cria um novo departamento
	 * @param actionEvent
	 */
	public void createNewDepartment(ActionEvent actionEvent){
		DepartmentImpl departmentImpl = new DepartmentImpl();
		UserImpl userImpl = new UserImpl();
		FacesMessage msg = null;
		
		/*
		 * Validações 
		 */
		if(this.getDepartment().getDepartamentCode() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o código do departamento.");
		}else if(this.getSelectedBoss() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome do departamento.");
		}else{
			this.getDepartment().setRegistration(userImpl.getUser(this.getSelectedBoss()));
			
			try{
				departmentImpl.save(this.getDepartment());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Departamento " + this.getDepartment().getDepartmentName() + " criado.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao criar o departamento.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Atualiza usuario
	 * @param actionEvent
	 */
	public void updateDepartment(ActionEvent actionEvent){
		DepartmentImpl departmentImpl = new DepartmentImpl();
		FacesMessage msg = null;
		
		/*
		 * Validações 
		 */
		
		if(this.getDepartment().getDepartamentCode() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o código do departamento.");
		}else if(this.getDepartment().getDepartmentName() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar o nome do departamento.");
		}else if(this.getDepartment().getRegistration() == null){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "É necessário informar um chefe de departamento.");
		}else{
			
			try{
				departmentImpl.update(this.getDepartment());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Departamento " + this.getDepartment().getDepartmentName() + " criado.");
			}catch(RuntimeException e){
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Ocorreu um erro ao criar o departamento.");
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public HashMap<String, String> getHeads() {
		HashMap<String, String> boss = new HashMap<String, String>();
		List<User> users = new ArrayList<User>();
		
		UserImpl userImpl = new UserImpl();
		users = userImpl.getUsersList();
		
		for(User u : users){
			for(Role r : u.getRole()){
				if(r.getName().equals("ROLE_BOSS")){
					boss.put(u.getName(), u.getRegistration());
					break;
				}
			}
		}
		
		return boss;
	}

	/*
	 * Getters e Setters não utilizados
	 */
	
	public void setHeads(HashMap<String, String> heads) {
		this.heads = heads;
	}

}
