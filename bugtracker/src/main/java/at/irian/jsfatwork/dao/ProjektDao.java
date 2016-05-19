package at.irian.jsfatwork.dao;

import java.util.List;

import at.irian.jsfatwork.domain.Projekt;

public interface ProjektDao {
	public void makeTransient(Projekt projekt);
	public void makePersistant(Projekt projekt);
	public Projekt findByNr(Integer nr);
	public List<Projekt> findAll();
}
