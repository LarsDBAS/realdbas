package at.irian.jsfatwork.gui.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.eclipse.persistence.internal.libraries.antlr.runtime.debug.DebugEventHub;

import at.irian.jsfatwork.domain.FehlerReport;
import at.irian.jsfatwork.domain.FehlerReport2;
import at.irian.jsfatwork.domain.Nutzer;
import at.irian.jsfatwork.domain.Status;
import at.irian.jsfatwork.service.FehlerServiceImpl;
import at.irian.jsfatwork.service.StatusServiceImpl;

@ManagedBean(name="reportBean")
@RequestScoped
public class ReportBean {
	List<FehlerReport> frList = null;
	List<FehlerReport2> frList2 = null;
	FehlerServiceImpl fsi = new FehlerServiceImpl();
	Nutzer nutzer;
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
		load();
	}
	
	private void load(){
		List<Status> stati = ssi.showAllStati();
		this.frList = new ArrayList<FehlerReport>();
		FehlerReport fr = null;
		for(Status s: stati){
			fr = new FehlerReport();
			fr.setStatus(s.getBezeichnung());
			fr.setAnzahl(fsi.findByStatus(s).size());
			frList.add(fr);

			
		}
		
		
	}
		
		public String ladeDaten(Nutzer n){
			List<Status> stati = ssi.showAllStati();
			this.frList = new ArrayList<FehlerReport>();
			FehlerReport fr = null;
			for(Status s: stati){
				fr = new FehlerReport();
				fr.setStatus(s.getBezeichnung());
				fr.setAnzahl(fsi.findByStatusUndNutzer(s, n).size());
				frList.add(fr);

				
			}
			return "/showReports.xhtml";
	}
}
