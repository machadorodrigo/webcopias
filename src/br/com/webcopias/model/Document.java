package br.com.webcopias.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@DynamicUpdate
@Table(appliesTo="DOCUMENTO")
public class Document {
	private Integer id,documentType;
	private String documentName,documentPath,documentDescription;
	private Double documentSize;

	public Document(){};
	
	public Document(Integer id, Integer documentType, String documentName, String documentPath, String documentDescription, Double documentSize) {
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
	
	public Integer getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(Integer documentType) {
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
	
	public Double getDocumentSize() {
		return documentSize;
	}
	
	public void setDocumentSize(Double documentSize) {
		this.documentSize = documentSize;
	}
}
