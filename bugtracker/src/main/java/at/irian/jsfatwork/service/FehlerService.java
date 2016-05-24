package at.irian.jsfatwork.service;

import java.util.Date;
import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Nutzer;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public interface FehlerService {
	public void save(Fehler fehler);
	public void delete(Fehler fehler);
	public List<Fehler> findByProjekt(Projekt projekt);
	public List<Fehler> findByStatus(Status status);
	public List<Fehler> findByStatusUndNutzer(Status status, Nutzer nutzer);
	public List<Fehler> findByDateRange(Date begin, Date end);
	public Fehler findByFehler(Integer feNr);
	String findNutzer(Integer nutzerNr);
}
