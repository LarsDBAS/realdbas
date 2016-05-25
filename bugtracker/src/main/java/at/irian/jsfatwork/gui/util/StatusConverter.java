package at.irian.jsfatwork.gui.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.*;
import at.irian.jsfatwork.service.EntitySingleton;

@FacesConverter("at.irian.jsfatwork.gui.util.StatusConverter")
public class StatusConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Status result = null;
		TypedQuery<Status> q = EntitySingleton.getEntitymanager().createQuery(""
				+ "From Status As s Where s.nuNr =:stanr", Status.class);
		q.setParameter("stanr", Integer.valueOf(arg2));
		result = q.getSingleResult();
		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		return String.valueOf(arg2);
	}

}
