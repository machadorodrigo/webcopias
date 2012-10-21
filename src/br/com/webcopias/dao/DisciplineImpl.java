package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Discipline;
import br.com.webcopias.utils.HibernateUtil;

public class DisciplineImpl extends GenericHibernate<Discipline> implements DisciplineDao{

	@Override
	public Discipline getDiscipline(String code) {
		Discipline discipline = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			discipline = (Discipline) session.get(Discipline.class, code);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return discipline;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Discipline> getDisciplinesList() {
		List<Discipline> disciplines = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			disciplines = session.createQuery("from Discipline").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return disciplines;
    }

}
