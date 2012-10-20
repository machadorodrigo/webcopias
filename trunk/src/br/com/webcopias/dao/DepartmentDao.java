package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.Department;

public interface DepartmentDao extends GenericRepository<Department> {
	public Department getDepartment(String code);
	public List<Department> getDepartmentsList();
}
