package at.irian.jsfatwork.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.irian.jsfatwork.dao.FehlerDaoImpl;
import at.irian.jsfatwork.dao.KommentarDaoImpl;
import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Kommentar;
@ManagedBean
public class KommentarServiceImpl implements KommentarService {
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
	    
	public KommentarServiceImpl(){
		this.entityManager = EntitySingleton.getEntitymanager();
	}

	@Override
	public void save(Kommentar kommentar) {
		KommentarDaoImpl kdi = new KommentarDaoImpl();
		kdi.setEntityManager(this.entityManager);
		this.entityManager.getTransaction().begin();
		if (!this.entityManager.contains(kommentar)) {
			  kdi.makePersistant(kommentar);
			} else {
			  kdi.merge(kommentar);
			}
		this.entityManager.getTransaction().commit();
		
	}

	@Override
	public void delete(Kommentar kommentar) {
		KommentarDaoImpl kdi = new KommentarDaoImpl();
		kdi.setEntityManager(this.entityManager);
		this.entityManager.getTransaction().begin();
		kdi.makeTransient(kommentar);
		this.entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Kommentar> findByFehler(Fehler fehler) {
		KommentarDaoImpl kdi = new KommentarDaoImpl();
		kdi.setEntityManager(this.entityManager);
		List<Kommentar> result = null;
		//this.entityManager.refresh(Fehler.class);
		this.entityManager.getTransaction().begin();
		result = kdi.findByFehler(fehler);
		this.entityManager.getTransaction().commit();
		return result;
	}



}
