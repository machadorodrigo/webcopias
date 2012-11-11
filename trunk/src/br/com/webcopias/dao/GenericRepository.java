package br.com.webcopias.dao;

public interface GenericRepository<E> {
	void update(E entity);

	void merge(E entity);

	boolean remove(final E entity);

	E save(E entity);

	int execute(String query);
}
