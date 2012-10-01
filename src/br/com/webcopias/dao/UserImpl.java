package br.com.webcopias.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.User;
import br.com.webcopias.utils.HibernateUtil;

public class UserImpl extends GenericHibernate<User> implements UserDao{
	
	public User getUser(String registration) {
		User user = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.get(User.class, registration);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
    }

}
