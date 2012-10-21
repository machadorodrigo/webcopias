package br.com.webcopias.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.User;

@FacesConverter(value="userConverter")
public class UserConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UserImpl userImpl = new UserImpl();
		User user = null;
		
		try {
			user = userImpl.getUser(value);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		User user = new User();
		user = (User) value;
		
		return user.getRegistration();
	}

}
