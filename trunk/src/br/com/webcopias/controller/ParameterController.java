package br.com.webcopias.controller;

import java.io.File;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;

import br.com.webcopias.dao.ParameterImpl;
import br.com.webcopias.model.Parameter;

@ManagedBean(name="parameter")
@SessionScoped
public class ParameterController {
	private Parameter parameter;

	public ParameterController(){
		parameter = new Parameter();
		
		ParameterImpl parameterImpl = new ParameterImpl();
		List<Parameter> paramList = parameterImpl.getParametersList();
		
		if(paramList.size() == 0){
			parameter.setSmtpServer("");
			parameter.setVolume("");
		}else{
			parameter = paramList.get(0);
		}
	}
	
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * Se existir algum parametro apenas atualiza, caso nao exista, cria um novo registro
	 * @param actionEvent
	 */
	public void updateService(ActionEvent actionEvent) {
		ParameterImpl parameterImpl = new ParameterImpl();
		FacesMessage msg = this.validateParameter();
		
		if(parameterImpl.getParametersList().size() != 0){
			if(msg == null){
				try{
					parameterImpl.update(parameter);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
							"Os parâmetros foram atualizados com sucesso.");
				}catch(HibernateException e){
					e.printStackTrace();
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Ocorreu um erro ao atualizar os parâmetros.");
				}
			}
		}else{
			if(msg == null){
				try{
					parameterImpl.save(parameter);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
							"Os parâmetros foram atualizados com sucesso.");
				}catch(HibernateException e){
					e.printStackTrace();
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Ocorreu um erro ao atualizar os parâmetros.");
				}
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Realiza validações gerais
	 * @return Retorna nulo caso nao ocorra nenhum erro ou retorna a mensagem de erro
	 */
	private FacesMessage validateParameter(){
		FacesMessage msg = null;
		String volume = this.checkAndCreateFolder(this.getParameter().getVolume());
		if(this.getParameter().getVolume() == null || this.getParameter().getVolume().equals("")){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
					"É necessário informar o diretório de arquivos.");
		}else if(this.getParameter().getSmtpServer() == null || this.getParameter().getSmtpServer().equals("")){
			this.getParameter().setSmtpServer("");
		}else if(volume == null ){
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
					"Ocorreu um erro ao tentar criar os diretórios.");
		}
		
		return msg;
	}
	
	/**
	 * Verifica se a pasta existe e cria caso nao exista
	 * @param dir Diretorio escolhido para criar as demais pastas
	 * @return O diretorio criado
	 */
	private String checkAndCreateFolder(String dir){
		File path = new File(dir);
		String createdDir = null;
		try{
			if(path.exists()){
				
				File mainDir = new File(path.getAbsolutePath()+"/WebCopias");
				if(!mainDir.exists()) mainDir.mkdir();
				
				File documentDir = new File(mainDir.getAbsolutePath()+"/document");
				if(!documentDir.exists()) documentDir.mkdir();
				
				File uploadDir = new File(mainDir.getAbsolutePath()+"/upload");
				if(!uploadDir.exists()) uploadDir.mkdir();
				
				File userDir = new File(mainDir.getAbsolutePath()+"/user");
				if(!userDir.exists()) userDir.mkdir();
				
				createdDir = mainDir.getAbsolutePath();
			}else if(!path.exists()){
				path.mkdir();
				
				File mainDir = new File(path.getAbsolutePath()+"/WebCopias");
				mainDir.mkdir();
				
				File documentDir = new File(mainDir.getAbsolutePath()+"/document");
				documentDir.mkdir();
				
				File uploadDir = new File(mainDir.getAbsolutePath()+"/upload");
				uploadDir.mkdir();
				
				File userDir = new File(mainDir.getAbsolutePath()+"/user");
				userDir.mkdir();
				
				createdDir = mainDir.getAbsolutePath();
			}
		}catch(SecurityException e){
			e.printStackTrace();
			return null;
		}finally{
			System.gc();
		}
		
		return createdDir;
	}
}
