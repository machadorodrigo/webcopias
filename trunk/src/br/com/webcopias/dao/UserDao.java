package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.User;

public interface UserDao extends GenericRepository<User>{
	public User getUser(String registration);
	public List<User> getUsersList();
}
