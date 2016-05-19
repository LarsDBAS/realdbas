package at.irian.jsfatwork.gui.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.*;
import at.irian.jsfatwork.service.EntitySingleton;

@FacesConverter("at.irian.jsfatwork.gui.util.FehlerConverter")
public class FehlerConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Fehler result = new Fehler();
		TypedQuery<Fehler> q;
		try{
			q = EntitySingleton.getEntitymanager().createQuery(""
					+ "From Fehler As f Where f.feNr =:fenr", Fehler.class);
			q.setParameter("fenr", Integer.valueOf(arg2));
			result = q.getSingleResult();
		} catch (Exception e){
			q = EntitySingleton.getEntitymanager().createQuery(""
					+ "From Fehler As f Where f.feNr =1", Fehler.class);
			result = q.getSingleResult();
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		return String.valueOf(arg2);
	}

}