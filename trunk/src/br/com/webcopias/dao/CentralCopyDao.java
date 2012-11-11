package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.CentralCopy;

public interface CentralCopyDao extends GenericRepository<CentralCopy>{
	public CentralCopy getCentralCopy(int id);
	public List<CentralCopy> getCentralCopyList();
}
