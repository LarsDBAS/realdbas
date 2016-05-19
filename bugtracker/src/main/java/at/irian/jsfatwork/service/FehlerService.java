package at.irian.jsfatwork.service;

import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public interface FehlerService {
	public void save(Fehler fehler);
	public void delete(Fehler fehler);
	public List<Fehler> findByProjekt(Projekt projekt);
	public List<Fehler> findByStatus(Status status);
	public Fehler findByFehler(Integer feNr);
}
