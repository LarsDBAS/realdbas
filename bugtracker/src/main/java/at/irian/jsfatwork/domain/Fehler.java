package at.irian.jsfatwork.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;

@Entity
public class Fehler {
	
    @NotNull
    @Id
    private Integer feNr;
    @NotNull
    @Min(value = 5)
    @Max(value = 64)
    private String bezeichnung;
    @NotNull
    @Min(value = 5)
    @Max(value = 2000)
    private String beschreibung;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date erstellt;
    @NotNull
    private String status;
    @NotNull
    private Integer prioritaet;
    @ManyToOne
    @JoinColumn(name = "erstelltVonNuNr")
    private Nutzer erstelltVon;
    

	@ManyToOne
    @JoinColumn (name = "zugewiesenAnNuNr")
    private Nutzer zugewiesenAn;
    
    @ManyToOne
    @JoinColumn (name = "staNr")
    private Status inStatus;
    

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "proNr")
	private Projekt projekt;
    
    @ManyToMany
    @JoinColumn(name = "feNr")
    private List<Fehler> verweistAuf;
    
    public Fehler() {
		super();
	}

	public Fehler(String beschreibung, String bezeichnung, Integer feNr, Date erstellt, String status, Integer prioritaet) {
		this.beschreibung = beschreibung;
		this.bezeichnung = bezeichnung;
		this.feNr = feNr;
		this.erstellt = erstellt;
		this.status=status;
		this.prioritaet=prioritaet;
	}
	public Fehler(Projekt projekt) {
		this.setProjekt(projekt);
	}
	
	@Override
	public boolean equals(Object obj) {
		Boolean res = false;
		 if ( obj instanceof Fehler  ){
			 Fehler f = (Fehler)obj;
			 if(f.getFeNr() == this.feNr){
				 res = true;
			 }
			 else {
				 res = false;
			 }
		 } 
		 return res;
	}
	


	@Override
	public String toString() {
		return String.valueOf(this.feNr);
	}

	public Integer getFeNr() {
		return feNr;
	}

	public void setFeNr(Integer feNr) {
		this.feNr = feNr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Date getErstellt() {
		return erstellt;
	}

	public void setErstellt(Date erstellt) {
		this.erstellt = erstellt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public Nutzer getErstelltVon() {
		return erstelltVon;
	}

	public void setErstelltVon(Nutzer erstelltVon) {
		this.erstelltVon = erstelltVon;
	}
	
    public Nutzer getZugewiesenAn() {
		return zugewiesenAn;
	}

	public void setZugewiesenAn(Nutzer zugewiesenAn) {
		this.zugewiesenAn = zugewiesenAn;
	}

	public Status getInStatus() {
		return inStatus;
	}

	public void setInStatus(Status inStatus) {
		this.inStatus = inStatus;
	}
	
    public Integer getPrioritaet() {
		return prioritaet;
	}

	public void setPrioritaet(Integer prioritaet) {
		this.prioritaet = prioritaet;
	}

	public List<Fehler> getVerweistAuf() {
		return verweistAuf;
	}

	public void setVerweistAuf(List<Fehler> verweistAuf) {
		this.verweistAuf = verweistAuf;
	}

 
}