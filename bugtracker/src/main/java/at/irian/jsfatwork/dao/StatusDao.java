package at.irian.jsfatwork.dao;

import java.util.List;

import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public interface StatusDao {
	public List<Status> findAll();
}
