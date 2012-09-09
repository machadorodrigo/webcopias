package br.com.webcopias.dao;

import java.io.Serializable;
import java.util.List;

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
	}

	@Override
	public void merge(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.merge(entity);
		t.commit();
	}

	@Override
	public void remove(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(entity);
		t.commit();
	}

	@Override
	public E save(E entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(entity);
		t.commit();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findById(Class<?> clazz, Serializable oid) {
		return (E) getHibernateTemplate().get(clazz, oid);
	}

	@Override
	public List<E> getList(Class<E> clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

	@Override
	public List<E> getListEnabled(Class<E> clazz) {
		String nameClass = clazz.getName();
		@SuppressWarnings("unchecked")
		List<E> list = getHibernateTemplate().find("from " + nameClass + " where disabledRecord = " + false);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> find(String query, Object[] param) {
		return getHibernateTemplate().find(query, param);
	}

	@Override
	public int execute(String query) {
		Query q = this.getSession().createQuery(query);
		int ret = q.executeUpdate();
		
		getHibernateTemplate().flush();
		
		return ret;
	}

}
