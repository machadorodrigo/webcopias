package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.Service;

public interface ServiceDao extends GenericRepository<Service>{
	
	public Service getService(int id);
	public List<Service> getServicesList();
}
