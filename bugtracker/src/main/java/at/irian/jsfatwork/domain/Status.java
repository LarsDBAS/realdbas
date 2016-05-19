package at.irian.jsfatwork.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Status {
	@Id
	private int staNr;
	@NotNull
	private String bezeichnung;
	
	public Status() {
		this.staNr = 0;
		this.bezeichnung = "";
	}
	public int getStaNr() {
		return staNr;
	}
	public void setStaNr(int staNr) {
		this.staNr = staNr;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	

}
