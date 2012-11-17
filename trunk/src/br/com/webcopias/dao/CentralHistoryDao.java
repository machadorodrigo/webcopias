package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.CentralHistory;

public interface CentralHistoryDao extends GenericRepository<CentralHistory> {
	public CentralHistory getCentralHistory(int id);
	public CentralHistory getCentralHistoryByDocument(int id);
	public List<CentralHistory> getCentralHistoryList();
}
