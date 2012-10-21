package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.Discipline;

public interface DisciplineDao extends GenericRepository<Discipline>{
	public Discipline getDiscipline(String code);
	public List<Discipline> getDisciplinesList();
}
