package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.Parameter;

public interface ParameterDao extends GenericRepository<Parameter> {
	public Parameter getParameter(int id);
	public List<Parameter> getParametersList();
}
