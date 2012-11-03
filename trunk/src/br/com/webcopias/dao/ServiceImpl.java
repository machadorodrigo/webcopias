package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Service;
import br.com.webcopias.utils.HibernateUtil;

public class ServiceImpl extends GenericHibernate<Service> implements ServiceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getServicesList() {
		List<Service> services = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			services = session.createQuery("from Service").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return services;
	}
	
	@Override
	public Service getService(int id) {
		Service service = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			service = (Service) session.get(Service.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return service;
	}
}
