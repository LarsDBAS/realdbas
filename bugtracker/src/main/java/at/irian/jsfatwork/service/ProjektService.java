package at.irian.jsfatwork.service;

import java.util.List;

import at.irian.jsfatwork.domain.Projekt;

public interface ProjektService {
	public void save(Projekt projekt);
	public void delete(Projekt projekt);
	public List<Projekt> showAllProjects();
	public Projekt getProjektByNr(int proNr);
}
