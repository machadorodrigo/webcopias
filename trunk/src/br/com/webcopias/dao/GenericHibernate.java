package br.com.webcopias.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericHibernate<E> extends HibernateDaoSupport implements GenericRepository<E> {

	@Override
	public void update(final E entity) {
		getHibernateTemplate().update(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void merge(E entity) {
		getHibernateTemplate().merge(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void remove(E entity) {
		getHibernateTemplate().delete(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public E save(E entity) {
		getHibernateTemplate().persist(entity);
		getHibernateTemplate().flush();
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
