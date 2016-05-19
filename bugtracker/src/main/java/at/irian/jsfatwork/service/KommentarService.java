package at.irian.jsfatwork.service;

import java.util.List;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Kommentar;

public interface KommentarService {
	public void save(Kommentar kommentar);
	public void delete(Kommentar kommentar);
	public List<Kommentar> findByFehler(Fehler fehler);

}
