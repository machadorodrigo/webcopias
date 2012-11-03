package br.com.webcopias.dao;

import java.util.List;

import br.com.webcopias.model.Document;

public interface DocumentDao extends GenericRepository<Document> {
	public Document getDocument(int id);
	public List<Document> getDocumentsList();
}
