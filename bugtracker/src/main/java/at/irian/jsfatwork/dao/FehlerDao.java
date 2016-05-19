package at.irian.jsfatwork.dao;

import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public interface FehlerDao {

	void makeTransient(Fehler fehler);
	void makePersistant(Fehler fehler);
	List<Fehler> findByProjekt(Projekt projekt);
	List<Fehler> findByStatus(Status status);
	void merge(Fehler fehler);
	Fehler findById(Integer feNr);
}
