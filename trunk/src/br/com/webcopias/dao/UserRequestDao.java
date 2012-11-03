package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.User;
import br.com.webcopias.model.UserRequest;

public interface UserRequestDao extends GenericRepository<UserRequest>{
	
	public List<UserRequest> getRequestByUser(User user);
	public UserRequest getUserRequest(int id);
	public List<UserRequest> getUserRequestList();	

}
