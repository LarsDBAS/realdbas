package at.irian.jsfatwork.gui.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.eclipse.persistence.internal.libraries.antlr.runtime.debug.DebugEventHub;

import at.irian.jsfatwork.domain.FehlerReport;
import at.irian.jsfatwork.domain.Status;
import at.irian.jsfatwork.service.FehlerServiceImpl;
import at.irian.jsfatwork.service.StatusServiceImpl;

@ManagedBean(name="reportBean")
@RequestScoped
public class ReportBean {
	List<FehlerReport> frList = null;
	FehlerServiceImpl fsi = new FehlerServiceImpl();
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
}
