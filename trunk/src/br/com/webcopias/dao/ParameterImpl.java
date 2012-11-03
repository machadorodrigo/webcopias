package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Parameter;
import br.com.webcopias.utils.HibernateUtil;

public class ParameterImpl extends GenericHibernate<Parameter> implements ParameterDao{

	@Override
	public Parameter getParameter(int id) {
		Parameter parameter = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			parameter = (Parameter) session.get(Parameter.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return parameter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parameter> getParametersList() {
		List<Parameter> parameters = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			parameters = session.createQuery("from Parameter").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return parameters;
	}


}
