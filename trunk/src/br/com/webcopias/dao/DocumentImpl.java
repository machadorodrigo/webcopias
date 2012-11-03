package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Document;
import br.com.webcopias.utils.HibernateUtil;

public class DocumentImpl extends GenericHibernate<Document> implements DocumentDao {

	@Override
	public Document getDocument(int id) {
		Document document = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			document = (Document) session.get(Document.class, id);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getDocumentsList() {
		List<Document> document = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			document = session.createQuery("from Document").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return document;
	}


}
