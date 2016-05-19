package at.irian.jsfatwork.dao;

import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Kommentar;

public interface KommentarDao {
	public void makeTransient(Kommentar kommentar);
	public void makePersistant(Kommentar kommentar);
	public List<Kommentar> findByFehler(Fehler fehler);
	public void merge(Kommentar kommentar);
}
