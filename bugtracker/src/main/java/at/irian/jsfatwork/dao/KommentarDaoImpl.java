package at.irian.jsfatwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Kommentar;

public class KommentarDaoImpl implements KommentarDao {
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
	public void makeTransient(Kommentar kommentar) {
		Query q = entityManager.createNativeQuery(""
				+ "DELETE FROM KOMMENTAR WHERE KONR = " +kommentar.getKoNr());
		q.executeUpdate();
		
	}

	@Override
	public void makePersistant(Kommentar kommentar) {
		int koNr = 1;
		Query q = entityManager.createQuery("" +
				"Select max(k.koNr)+1 From Kommentar as k");
		if (q.getSingleResult() != null) { 
			koNr = (Integer) q.getSingleResult();
		}

		kommentar.setKoNr(koNr);
        try{
        	entityManager.persist(kommentar);
        }catch(javax.persistence.EntityExistsException e){
        	//TODO Wenn das Objekt schon persistent ist,dann wird eine DataStoreException geworfen
        	
        }
	}

	@Override
	public List<Kommentar> findByFehler(Fehler fehler) {
		List<Kommentar> result;
		entityManager.getEntityManagerFactory().getCache().evictAll();
		TypedQuery<Kommentar> q = entityManager.createQuery(""+
				"From Kommentar As k WHERE k.fehler = :fehler", Kommentar.class);
		q.setParameter("fehler", fehler);
		result = q.getResultList();
		return result;
	}

	@Override
	public void merge(Kommentar kommentar) {
        try{
        	entityManager.merge(kommentar);
        }catch(javax.persistence.EntityExistsException e){
        	//TODO Wenn das Objekt schon persistent ist,dann wird eine DataStoreException geworfen
        	
        }
		
	}

}
