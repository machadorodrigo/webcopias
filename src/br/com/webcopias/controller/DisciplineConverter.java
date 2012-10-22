package br.com.webcopias.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.webcopias.dao.DisciplineImpl;
import br.com.webcopias.model.Discipline;

@FacesConverter(value="disciplineConverter")
public class DisciplineConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		DisciplineImpl disciplineImpl = new DisciplineImpl();
		Discipline discipline = null;
		
		try {
			discipline = disciplineImpl.getDiscipline(value);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		
		return discipline;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Discipline discipline = new Discipline();
		discipline = (Discipline) value;
		
		return discipline.getDisciplineCode();
	}
}
