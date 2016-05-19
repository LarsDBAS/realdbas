package at.irian.jsfatwork.gui.page;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.irian.jsfatwork.domain.Kommentar;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.service.ProjektService;
import at.irian.jsfatwork.service.ProjektServiceImpl;

@ManagedBean(name = "projektBean")
@RequestScoped
public class ProjektBean {
	private ProjektServiceImpl ps = new ProjektServiceImpl();
	private List<Projekt> projekte = null;
    
    public String edit(){
    	return "/showErrors.xhtml";
    }
    
	
    public ProjektBean() {
		super();
		this.projekte = ps.showAllProjects();
	}


	public List<Projekt> getProjekte(){
    	projekte=this.projekte;
    	return projekte;
    }
}
