package at.irian.jsfatwork.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.eclipse.persistence.jpa.config.Cascade;

@Entity 
public class Kommentar {
    @NotNull
    @Id
    private Integer koNr;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date erstellt;
    @NotNull
    @Min(value = 5)
    @Max(value = 2000)
    private String text;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinColumn(name = "feNr")
    private Fehler fehler = null;
    @ManyToOne
    @JoinColumn(name = "nuNr")
    private Nutzer erstelltVon;

    public Fehler getFehler() {
		return fehler;
	}

	public void setFehler(Fehler fehler) {
		this.fehler = fehler;
	}

	public Kommentar() {
		super();
	}

	public Kommentar(String text, Integer koNr, Date erstellt) {
		this.text = text;
		this.koNr = koNr;
		this.erstellt = erstellt;
	}
	public Integer getKoNr() {
		return koNr;
	}

	public void setKoNr(Integer koNr) {
		this.koNr = koNr;
	}

	public Date getErstellt() {
		return erstellt;
	}

	public void setErstellt(Date erstellt) {
		this.erstellt = erstellt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Nutzer getErstelltVon() {
		return erstelltVon;
	}

	public void setErstelltVon(Nutzer erstelltVon) {
		this.erstelltVon = erstelltVon;
	}

}