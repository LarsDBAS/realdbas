package at.irian.jsfatwork.dao;

import java.util.Date;
import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Nutzer;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public interface FehlerDao {

	void makeTransient(Fehler fehler);
	void makePersistant(Fehler fehler);
	List<Fehler> findByProjekt(Projekt projekt);
	List<Fehler> findByStatus(Status status);
	List<Fehler> findByStatusUndNutzer(Status status, Nutzer nutzer);
	List<Fehler> findByDateRange(Date begin, Date end);
	void merge(Fehler fehler);
	Fehler findById(Integer feNr);
	String findNutzerNr(Integer nutzerNr);
}
