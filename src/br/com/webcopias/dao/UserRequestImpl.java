package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.User;
import br.com.webcopias.model.UserRequest;
import br.com.webcopias.utils.HibernateUtil;

public class UserRequestImpl extends GenericHibernate<UserRequest> implements UserRequestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRequest> getRequestByUser(User user) {
		List<UserRequest> userRequest = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			userRequest = session.createQuery("from UserRequest where user_registration = '"
			+ user.getRegistration() +"'").list();
			
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return userRequest;
	}

	@Override
	public UserRequest getUserRequest(int id) {
		UserRequest userRequest = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			userRequest = (UserRequest) session.get(UserRequest.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userRequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRequest> getUserRequestList() {
		List<UserRequest> userRequest = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			userRequest = session.createQuery("from UserRequest").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return userRequest;
	}

}
