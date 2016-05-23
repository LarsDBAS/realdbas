package at.irian.jsfatwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public class FehlerDaoImpl implements FehlerDao {
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
	public void makeTransient(Fehler fehler) {
//		entityManager.find(Fehler.class, fehler.getFeNr());
//		entityManager.remove(fehler);
		Query q1 = entityManager.createNativeQuery(""
				+ "DELETE FROM KOMMENTAR WHERE FENR = " +fehler.getFeNr());
		Query q2 = entityManager.createNativeQuery(""
				+ "DELETE FROM FEHLER_FEHLER WHERE Fehler_FENR = " +fehler.getFeNr());
		Query q3 = entityManager.createNativeQuery(""
				+ "DELETE FROM FEHLER WHERE FENR = " +fehler.getFeNr());
		q1.executeUpdate();
		q2.executeUpdate();
		q3.executeUpdate();
		
	}

	@Override
	public void makePersistant(Fehler fehler) {
		int feNr = 1;
		Query q = entityManager.createQuery("" +
				"Select max(f.feNr)+1 From Fehler as f");
		if (q.getSingleResult() != null) { 
			feNr = (Integer) q.getSingleResult();
		}

		fehler.setFeNr(feNr);
        try{
        	entityManager.persist(fehler);
        }catch(javax.persistence.EntityExistsException e){
        	//TODO Wenn das Objekt schon persistent ist,dann wird eine DataStoreException geworfen
        	
        }
		
	}

	@Override
	public List<Fehler> findByProjekt(Projekt projekt) {
		List<Fehler> result;
		entityManager.getEntityManagerFactory().getCache().evictAll();
		TypedQuery<Fehler> q = entityManager.createQuery(""+
				"From Fehler As f WHERE f.projekt = :projekt", Fehler.class);
		q.setParameter("projekt", projekt);
		result = q.getResultList();
		return result;
	}

	@Override
	public void merge(Fehler fehler) {
        try{
        	entityManager.merge(fehler);
        }catch(javax.persistence.EntityExistsException e){
        	//TODO Wenn das Objekt schon persistent ist,dann wird eine DataStoreException geworfen
        	
        }
	}

	@Override
	public List<Fehler> findByStatus(Status status) {
		List<Fehler> result;
		entityManager.getEntityManagerFactory().getCache().evictAll();
		TypedQuery<Fehler> q = entityManager.createQuery(""+
				"From Fehler As f WHERE f.inStatus = :status", Fehler.class);
		q.setParameter("status", status);
		result = q.getResultList();
		return result;
	}
	
	@Override
	public Fehler findById(Integer feNr) {
		Fehler result;
		entityManager.getEntityManagerFactory().getCache().evictAll();
		TypedQuery<Fehler> q = entityManager.createQuery(""+
				"From Fehler As f WHERE f.feNr = :feNr", Fehler.class);
		q.setParameter("feNr", feNr);
		result = q.getSingleResult();
		return result;
	}
	




}
