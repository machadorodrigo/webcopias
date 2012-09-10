package br.com.webcopias.dao;


import org.hibernate.Session;

import br.com.webcopias.model.User;
import br.com.webcopias.utils.HibernateUtil;

public class UserImpl extends GenericHibernate<User> implements UserDao{

	public User getUser(String registration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (User) session.load(User.class, registration);
    }

}
