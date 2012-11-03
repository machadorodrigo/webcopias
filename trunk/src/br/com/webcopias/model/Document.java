package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENTO")
public class Document {
	private Integer id;
	private String documentName,documentPath,documentDescription,documentType;
	private Long documentSize;

	public Document(){};
	
	public Document(Integer id, String documentType, String documentName, String documentPath, String documentDescription, Long documentSize) {
		super();
		this.id = id;
		this.documentType = documentType;
		this.documentName = documentName;
		this.documentPath = documentPath;
		this.documentDescription = documentDescription;
		this.documentSize = documentSize;
	}

	@Id
	@GeneratedValue
	@Column(unique=true)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public String getDocumentName() {
		return documentName;
	}
	
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getDocumentPath() {
		return documentPath;
	}
	
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
	public String getDocumentDescription() {
		return documentDescription;
	}
	
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	
	public Long getDocumentSize() {
		return documentSize;
	}
	
	public void setDocumentSize(Long documentSize) {
		this.documentSize = documentSize;
	}
}
