package at.irian.jsfatwork.gui.page;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Kommentar;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.service.FehlerService;
import at.irian.jsfatwork.service.FehlerServiceImpl;
import at.irian.jsfatwork.service.KommentarService;
import at.irian.jsfatwork.service.KommentarServiceImpl;
import at.irian.jsfatwork.service.ProjektServiceImpl;

@ManagedBean
@SessionScoped
public class FehlerBean {
	
	//Attribute die die aktuell aktiven Objekte referenzieren
	private Projekt projekt = null;
	private Fehler fehler = new Fehler();
	private Kommentar kommentar = new Kommentar();
	//Listen der Töchterelemente der aktiven Objekte
	private List<Fehler> fehlerList = null;
	private List<Kommentar> kommentarList = null;
	//Serviceklassen zum Herstellen der Persistenz
	private FehlerServiceImpl fsi = new FehlerServiceImpl();
	private KommentarServiceImpl ksi = new KommentarServiceImpl();
	private String reference = null;

	

	
    public String editFehler(Fehler fehler) {
        this.fehler = fehler;
        this.showComments(fehler);
        return "/showAndEditError.xhtml";
    }

	public String saveFehler(Projekt p) {
		fsi.save(this.fehler);
		this.showErrors(this.projekt);
		return "/showErrors.xhtml";
	}
	
	public String createFehler(Projekt p){
		this.fehler = new Fehler(p);
		return "/showAndEditError.xhtml";
	}
	
	public String deleteFehler(Fehler fehler){
		Projekt p = fehler.getProjekt();
		fsi.delete(fehler);
		this.fehler = new Fehler(p);
		this.showErrors(p);
		return "/showErrors.xhtml";
	}
	
    public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public Kommentar getKommentar() {
		return kommentar;
	}

	public void setKommentar(Kommentar kommentar) {
		this.kommentar = kommentar;
	}

	public List<Kommentar> getKommentarList() {
		return kommentarList;
	}

	public void setKommentarList(List<Kommentar> kommentarList) {
		this.kommentarList = kommentarList;
	}

	public String editKommentar(Kommentar kommentar) {
        this.kommentar = kommentar;
        return "/editComment.xhtml";
    }

	public String saveKommentar(Kommentar kommentar) {
		this.ksi.save(kommentar);
		this.showComments(kommentar.getFehler());
		return "/showAndEditError.html";
	}
	
	public String createKommentar(Fehler fehler){
		this.fehler = fehler;
		this.kommentar.setFehler(this.fehler);
		this.kommentar.setErstellt(new Date(System.currentTimeMillis()));
		this.saveKommentar(this.kommentar);
		this.kommentar = new Kommentar();
		return "/showAndEditError.xhtml";
	}
	
	
	
	public String deleteKommentar(Kommentar kommentar){
		Fehler f = kommentar.getFehler();
		this.ksi.delete(kommentar);
		this.showComments(f);
		this.kommentar = new Kommentar();
		this.kommentar.setFehler(f);
		return "/showAndEditError.xhtml";
	}
	
	public String addFehlerReference(Fehler fehlerToAddTo, String fehlerToAdd){
		if (!fehlerToAddTo.getVerweistAuf().contains(fsi.findByFehler(Integer.valueOf(fehlerToAdd)))){
			fehlerToAddTo.getVerweistAuf().add(fsi.findByFehler(Integer.valueOf(fehlerToAdd)));
		}
		return "/showAndEditError.xhtml";
	}
	
	public String removeFehlerReference(Fehler fehlerToRemoveFrom, Fehler fehlerToRemove){
		fehlerToRemoveFrom.getVerweistAuf().remove(fehlerToRemove);
		return "/showAndEditError.xhtml";
	}

    public Fehler getFehler() {
        return fehler;
    }
    

    public void setFehler(Fehler fehler) {
        this.fehler = fehler;
    }
    
    public String showErrors(Projekt p){
    	this.projekt = p;
    	this.fehlerList = null;
    	this.fehlerList = this.fsi.findByProjekt(p);
		return "/showErrors.xhtml";
    }
    
    public String showComments(Fehler f){
    	this.fehler = f;
    	this.kommentar = new Kommentar();
    	this.kommentarList = null;
    	this.kommentarList = this.ksi.findByFehler(f);
		return "/showAndEditError.xhtml";
    }

	public List<Fehler> getFehlerList() {
		return fehlerList;
	}

	public void setFehlerList(List<Fehler> fehlerList) {
		this.fehlerList = fehlerList;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
