package at.irian.jsfatwork.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Nutzer {
	@Id
	private int nuNr;
	@NotNull
	private String vorname, nachname;
	
	public Nutzer(){
		this.vorname = "";
		this.nachname = "";
		this.nuNr = 0;
	}

	@Override
	public boolean equals(Object obj) {
		Boolean res = false;
		 if ( obj instanceof Nutzer  ){
			 Nutzer n = (Nutzer)obj;
			 if(n.getNachname() == this.nachname && n.getVorname() == this.vorname && n.getNuNr() == this.nuNr){
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
		return String.valueOf(this.nuNr);
	}

	public int getNuNr() {
		return nuNr;
	}

	public void setNuNr(int nuNr) {
		this.nuNr = nuNr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	
	
}
