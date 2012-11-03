package br.com.webcopias.dao;

import java.util.Set;

import br.com.webcopias.model.Role;


public interface RoleDao extends GenericRepository<Role>{
	public Role getRole(String registration);
	public Set<Role> getRolesByList(String name);
}
