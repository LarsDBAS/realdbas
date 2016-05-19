package at.irian.jsfatwork.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.dao.FehlerDaoImpl;
import at.irian.jsfatwork.dao.ProjektDaoImpl;
import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

@ManagedBean
public class FehlerServiceImpl implements FehlerService {
	 private EntityManager entityManager;

	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    public EntityManager getEntityManager() {
	        if (entityManager == null)
	            throw new IllegalStateException(
	                    "EntityManager has not been set on DAO before usage");
	        return entityManager;
	    }


	@Override
	public void delete(Fehler fehler) {
		FehlerDaoImpl fdi = new FehlerDaoImpl();
		fdi.setEntityManager(this.entityManager);
		this.entityManager.getTransaction().begin();
		fdi.makeTransient(fehler);
		this.entityManager.getTransaction().commit();
		
	}

	public FehlerServiceImpl() {

	    this.entityManager = EntitySingleton.getEntitymanager();
	}

	@Override
	public List<Fehler> findByProjekt(Projekt projekt) {
		FehlerDaoImpl fdi = new FehlerDaoImpl();
		fdi.setEntityManager(this.entityManager);
		List<Fehler> result = null;
		//this.entityManager.refresh(Fehler.class);
		this.entityManager.getTransaction().begin();
		result = fdi.findByProjekt(projekt);
		this.entityManager.getTransaction().commit();
		return result;
	}

	@Override
	public void save(Fehler fehler) {
		FehlerDaoImpl fdi = new FehlerDaoImpl();
		fdi.setEntityManager(this.entityManager);
		this.entityManager.getTransaction().begin();
		if (!this.entityManager.contains(fehler)) {
			  fdi.makePersistant(fehler);
			} else {
			  fdi.merge(fehler);
			}
		this.entityManager.getTransaction().commit();

		
	}

	@Override
	public List<Fehler> findByStatus(Status status) {
		FehlerDaoImpl fdi = new FehlerDaoImpl();
		fdi.setEntityManager(this.entityManager);
		List<Fehler> result = null;
		//this.entityManager.refresh(Fehler.class);
		this.entityManager.getTransaction().begin();
		result = fdi.findByStatus(status);
		this.entityManager.getTransaction().commit();
		return result;
	}

	@Override
	public Fehler findByFehler(Integer feNr) {
		FehlerDaoImpl fdi = new FehlerDaoImpl();
		fdi.setEntityManager(this.entityManager);
		Fehler result = null;
		//this.entityManager.refresh(Fehler.class);
		this.entityManager.getTransaction().begin();
		result = fdi.findById(feNr);
		this.entityManager.getTransaction().commit();
		return result;
	}


}
