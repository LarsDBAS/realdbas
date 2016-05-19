package at.irian.jsfatwork.gui.page;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.Nutzer;
import at.irian.jsfatwork.service.*;

@ManagedBean
@RequestScoped
public class NutzerBean {

	List<Nutzer> nutzerOptions = null;
	
	public List<Nutzer> getNutzerOptions() {
		return nutzerOptions;
	}

	public void setNutzerOptions(List<Nutzer> nutzerOptions) {
		this.nutzerOptions = nutzerOptions;
	}

	@PostConstruct
	public void init(){
		List<Nutzer> result = null;
//		nutzerOptions = new ArrayList<SelectItem>();
		EntitySingleton.getEntitymanager().getTransaction().begin();
		TypedQuery<Nutzer> q = EntitySingleton.getEntitymanager().createQuery(""
				+ "From Nutzer As n", Nutzer.class);
		result = q.getResultList();
		EntitySingleton.getEntitymanager().getTransaction().commit();
//		for(Nutzer n:result){
//			this.nutzerOptions.add(new SelectItem(n.getNuNr(), n.getNachname()+", "+n.getVorname()));
//		}
		this.nutzerOptions = result;
		
	}

//	public List<SelectItem> getNutzerOptions() {
//		return nutzerOptions;
//	}
//
//	public void setNutzerOptions(List<SelectItem> nutzerOptions) {
//		this.nutzerOptions = nutzerOptions;
//	}

}
