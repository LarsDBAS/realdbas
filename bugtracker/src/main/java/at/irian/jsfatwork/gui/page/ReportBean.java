package at.irian.jsfatwork.gui.page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Query;

import org.eclipse.persistence.internal.libraries.antlr.runtime.debug.DebugEventHub;

import at.irian.jsfatwork.domain.FehlerReport;
import at.irian.jsfatwork.domain.FehlerReport2;
import at.irian.jsfatwork.domain.Nutzer;
import at.irian.jsfatwork.domain.Status;
import at.irian.jsfatwork.service.EntitySingleton;
import at.irian.jsfatwork.service.FehlerServiceImpl;
import at.irian.jsfatwork.service.StatusServiceImpl;

@ManagedBean(name="reportBean")
@RequestScoped
public class ReportBean {
	List<FehlerReport> frList = null;
	List<FehlerReport2> frList2 = null;
	public List<FehlerReport2> getFrList2() {
		return frList2;
	}


	public void setFrList2(List<FehlerReport2> frList2) {
		this.frList2 = frList2;
	}

	FehlerServiceImpl fsi = new FehlerServiceImpl();
	Nutzer nutzer;
	public Date getVon() {
		return von;
	}


	public void setVon(Date von) {
		this.von = von;
	}


	public Date getBis() {
		return bis;
	}


	public void setBis(Date bis) {
		this.bis = bis;
	}

	Date von = null;
	Date bis = null;
	public Nutzer getNutzer() {
		return nutzer;
	}


	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public List<FehlerReport> getFrList() {
		return frList;
	}

	public void setFrList(List<FehlerReport> frList) {
		this.frList = frList;
	}

	StatusServiceImpl ssi = new StatusServiceImpl();
	
	public ReportBean(){
		this.nutzer = EntitySingleton.getEntitymanager().createQuery("FROM Nutzer AS n",Nutzer.class).getResultList().get(0);
		this.von = EntitySingleton.getEntitymanager().createQuery(""
				+ "SELECT MIN(f.erstellt) FROM Fehler AS f", Date.class).getSingleResult();
		this.bis = EntitySingleton.getEntitymanager().createQuery(""
				+ "SELECT MAX(f.erstellt) FROM Fehler AS f", Date.class).getSingleResult();
		ladeDaten();
	}
	
//	private void load(){
//		this.nutzer = EntitySingleton.getEntitymanager().createQuery("FROM Nutzer AS n",Nutzer.class).getResultList().get(0);
//		List<Status> stati = ssi.showAllStati();
//		this.frList = new ArrayList<FehlerReport>();
//		FehlerReport fr = null;
//		for(Status s: stati){
//			fr = new FehlerReport();
//			fr.setStatus(s.getBezeichnung());
//			fr.setAnzahl(fsi.findByStatus(s).size());
//			frList.add(fr);
//
//			
//		}
//		this.von = EntitySingleton.getEntitymanager().createQuery(""
//				+ "SELECT MIN(f.erstellt) FROM Fehler AS f", Date.class).getSingleResult();
//		this.bis = EntitySingleton.getEntitymanager().createQuery(""
//				+ "SELECT MAX(f.erstellt) FROM Fehler AS f", Date.class).getSingleResult();
//		
//		List<Object[]> result = EntitySingleton.getEntitymanager().createQuery(""
//				+ "SELECT f.erstellt, COUNT(f.erstellt) FROM Fehler As f GROUP BY f.erstellt").getResultList();
//		frList2 = new ArrayList<FehlerReport2>();
//		FehlerReport2 fr2 = null;
//		for(Object[] o: result){
//			fr2 = new FehlerReport2();
//			fr2.setDatum(String.valueOf(new SimpleDateFormat("dd.MM.yyyy").format((Date)o[0])));
//			fr2.setAnzahl((Long)o[1]);
//			frList2.add(fr2);
//		}
//		
//		
//		
//	}
		
		public String ladeDaten(){
			if(this.nutzer != null){
				List<Status> stati = ssi.showAllStati();
				this.frList = new ArrayList<FehlerReport>();
				FehlerReport fr = null;
				for(Status s: stati){
					fr = new FehlerReport();
					fr.setStatus(s.getBezeichnung());
					fr.setAnzahl(fsi.findByStatusUndNutzer(s, this.nutzer).size());
					frList.add(fr);
	
					
				}
			}
			
			
			Query q = EntitySingleton.getEntitymanager().createQuery(""
					+ "SELECT f.erstellt, COUNT(f.erstellt) FROM Fehler As f GROUP BY f.erstellt HAVING f.erstellt BETWEEN :von AND :bis");
			q.setParameter("von", this.von);
			q.setParameter("bis",this.bis);
			List<Object[]> result = q.getResultList();
			frList2 = new ArrayList<FehlerReport2>();
			FehlerReport2 fr2 = null;
			for(Object[] o: result){
				fr2 = new FehlerReport2();
				fr2.setDatum(String.valueOf(new SimpleDateFormat("dd.MM.yyyy").format((Date)o[0])));
				fr2.setAnzahl((Long)o[1]);
				frList2.add(fr2);
			}
			
			return "/showReports.xhtml";
	}
}
