package br.com.webcopias.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.webcopias.dao.CentralCopyImpl;
import br.com.webcopias.dao.DocumentImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.CentralTaskInfoModel;
import br.com.webcopias.model.Document;
import br.com.webcopias.model.User;

@ManagedBean(name="central")
@SessionScoped
public class CentralCopyController {
	
	private CentralTaskInfoModel centralTaskInfoModel;
	
	public CentralCopyController(){}
	
	public DataModel<CentralTaskInfoModel> getListCentralInfo(){
		User user = new User();
		Document document = new Document();
		
		CentralCopyImpl centralCopyImpl = new CentralCopyImpl();
		UserImpl userImpl = new UserImpl();
		DocumentImpl documentImpl = new DocumentImpl();
		
		List<CentralTaskInfoModel> listTaskInfo = new ArrayList<CentralTaskInfoModel>();
		List<CentralCopy> listCentral = centralCopyImpl.getCentralCopyList();
		
		for(CentralCopy c : listCentral){
			user = userImpl.getUser(c.getUserRegistration());
			document = documentImpl.getDocument(c.getDocument());
			listTaskInfo.add(new CentralTaskInfoModel(user, document, c));
		}
		
		DataModel<CentralTaskInfoModel> model = new ListDataModel<CentralTaskInfoModel>(listTaskInfo);
		
		return model;
	}

	public CentralTaskInfoModel getCentralTaskInfoModel() {
		return centralTaskInfoModel;
	}

	public void setCentralTaskInfoModel(CentralTaskInfoModel centralTaskInfoModel) {
		this.centralTaskInfoModel = centralTaskInfoModel;
	}
}
