package br.com.webcopias.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<E> {
	void update(E entity);

	void merge(E entity);

	void remove(final E entity);

	E save(E entity);

	E findById(Class<?> clazz, Serializable oid);

	List<E> getList(final Class<E> clazz);

	List<E> getListEnabled(final Class<E> clazz);

	List<E> find(String query, Object[] param);
	
	int execute(String query);
}
