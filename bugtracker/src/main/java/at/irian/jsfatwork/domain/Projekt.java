package at.irian.jsfatwork.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
@Entity
public class Projekt {
    @NotNull
    @Id
    private Integer proNr;
    @NotNull
    @Min(value = 5)
    @Max(value = 64)
    private String bezeichnung;
  
    public Projekt() {
		super();
	}

	public Projekt(String bezeichnung, Integer proNr) {
		this.proNr = proNr;
		this.bezeichnung = bezeichnung;
	}
	
	@Column(name="proNr")
	public Integer getProNr() {
		return proNr;
	}

	public void setProNr(Integer proNr) {
		this.proNr = proNr;
	}
	
	@Column(name="BEZEICHNUNG")
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

 
}