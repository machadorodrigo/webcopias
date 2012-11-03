package br.com.webcopias.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.webcopias.dao.ServiceImpl;
import br.com.webcopias.model.Service;

@ManagedBean(name="service")
@SessionScoped
public class ServiceController {

	private Service service,selectedService;
	
	public ServiceController(){}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(Service selectedService) {
		this.selectedService = selectedService;
	}
	
	public DataModel<Service> getListServices() {

		ServiceImpl serviceImpl = new ServiceImpl();
		List<Service> services = serviceImpl.getServicesList();
		DataModel<Service> model = new ListDataModel<Service>(services);

		return model;
	}
	
	public void prepareCreateService(ActionEvent actionEvent) {
		this.setSelectedService(new Service());
	}

	public void createNewService(ActionEvent actionEvent) {
		ServiceImpl serviceImpl = new ServiceImpl();
		FacesMessage msg = null;

		if (this.getSelectedService().getServiceName() == null || this.getSelectedService().getServiceName().equals("")) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
					"É necessário informar a descrição do serviço.");
		} else if (this.getSelectedService().getServiceCost() == null || this.getSelectedService().getServiceCost().equals("")) {
			this.getSelectedService().setServiceCost(0.0);
		}else {
			try {
				serviceImpl.save(this.getSelectedService());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Serviço criado com sucesso");
			} catch (RuntimeException e) {
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Ocorreu um erro ao criar o sucesso.");
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void updateService(ActionEvent actionEvent) {
		ServiceImpl serviceImpl = new ServiceImpl();
		FacesMessage msg = null;

		if (this.getSelectedService().getServiceName() == null || this.getSelectedService().getServiceName().equals("")) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
					"É necessário informar a descrição do serviço.");
		} else if (this.getSelectedService().getServiceCost() == null || this.getSelectedService().getServiceCost().equals("")) {
			this.getSelectedService().setServiceCost(0.0);
		}else {
			try {
				serviceImpl.update(this.getSelectedService());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Serviço atualizado com sucesso");
			} catch (RuntimeException e) {
				e.printStackTrace();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Ocorreu um erro ao atualizar o serviço.");
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void deleteService(ActionEvent acEvent) {
		ServiceImpl serviceImpl = new ServiceImpl();
		FacesMessage msg = null;

		try {
			serviceImpl.remove(this.getSelectedService());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"O serviço foi removido.");
		} catch (RuntimeException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
					"Ocorreu um erro ao tentar remover o serviço.");
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
