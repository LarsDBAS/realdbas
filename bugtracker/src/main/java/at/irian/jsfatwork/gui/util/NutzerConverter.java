package at.irian.jsfatwork.gui.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.*;
import at.irian.jsfatwork.service.EntitySingleton;

@FacesConverter("at.irian.jsfatwork.gui.util.NutzerConverter")
public class NutzerConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Nutzer result = null;
		TypedQuery<Nutzer> q = EntitySingleton.getEntitymanager().createQuery(""
				+ "From Nutzer As n Where n.nuNr =:nunr", Nutzer.class);
		q.setParameter("nunr", Integer.valueOf(arg2));
		result = q.getSingleResult();
		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		return String.valueOf(arg2);
	}

}
