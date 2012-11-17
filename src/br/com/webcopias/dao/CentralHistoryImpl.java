package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.CentralHistory;
import br.com.webcopias.utils.HibernateUtil;

public class CentralHistoryImpl extends GenericHibernate<CentralHistory> implements CentralHistoryDao{

	@Override
	public CentralHistory getCentralHistory(int id) {
		CentralHistory centralHistory = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			centralHistory = (CentralHistory) session.get(CentralHistory.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return centralHistory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CentralHistory> getCentralHistoryList() {
		List<CentralHistory> centralHistories = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			centralHistories = session.createQuery("from CentralHistory").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return centralHistories;
	}

	@Override
	public CentralHistory getCentralHistoryByDocument(int id) {
        Session session = null;
        CentralHistory centralHistory = null;
        try{
        	session = HibernateUtil.getSessionFactory().openSession();
        	org.hibernate.Query query = session.createQuery("from CentralHistory where document = "+id+"");
        	centralHistory = (CentralHistory) query.uniqueResult();
        }catch(HibernateException e){
        	e.printStackTrace();
        }finally{
        	session.close();
        }
        
        return  centralHistory;
	}


}
