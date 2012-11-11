package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.utils.HibernateUtil;

public class CentralCopyImpl extends GenericHibernate<CentralCopy> implements CentralCopyDao {

	@Override
	public CentralCopy getCentralCopy(int id) {
		CentralCopy centralCopy = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			centralCopy = (CentralCopy) session.get(CentralCopy.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return centralCopy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CentralCopy> getCentralCopyList() {
		List<CentralCopy> centralCopy = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			centralCopy = session.createQuery("from CentralCopy").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return centralCopy;
	}

}
