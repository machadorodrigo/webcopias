package br.com.webcopias.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.webcopias.utils.HibernateUtil;

public abstract class GenericHibernate<E> extends HibernateDaoSupport implements GenericRepository<E> {
	
	@Override
	public void update(final E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = session.beginTransaction();
		session.update(entity);
		t.commit();
		session.flush();
		session.close();
	}

	@Override
	public void merge(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction t = session.beginTransaction();
		session.merge(entity);
		t.commit();
		session.flush();
		session.close();
	}

	@Override
	public void remove(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction t = session.beginTransaction();
		session.delete(entity);
		t.commit();
		session.flush();
		session.close();
	}

	@Override
	public E save(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = session.beginTransaction();
		session.save(entity);
		t.commit();
		session.flush();
		session.close();
		return entity;
	}

	@Override
	public int execute(String query) {
		Query q = this.getSession().createQuery(query);
		int ret = q.executeUpdate();
		
		getHibernateTemplate().flush();
		
		return ret;
	}

}
