package at.irian.jsfatwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.Projekt;

public class ProjektDaoImpl implements ProjektDao {
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
	public void makeTransient(Projekt projekt) {
		Query q = entityManager.createNativeQuery(""
				+ "DELETE FROM PROJEKT WHERE PRONR = " +projekt.getProNr());
		q.executeUpdate();
		
	}

	@Override
	public void makePersistant(Projekt p) {

		int proNr = 1;
		Query q = entityManager.createQuery("" +
				"Select max(p.proNr)+1 From Projekt as p");
		if (q.getSingleResult() != null) { 
			proNr = (Integer) q.getSingleResult();
		}

		p.setProNr(proNr);
        try{
        	entityManager.persist(p);
        }catch(javax.persistence.EntityExistsException e){
        	//TODO Wenn das Objekt schon persistent ist,dann wird eine DataStoreException geworfen
        	
        }
		
	}

	@Override
	public Projekt findByNr(Integer nr) {
		Projekt result;
		TypedQuery<Projekt> q = entityManager.createQuery(""+
				"From Projekt as p WHERE p.proNr = :proNr", Projekt.class);
		q.setParameter("proNr", nr);
		result = q.getSingleResult();
		return result;
	}

	@Override
	public List<Projekt> findAll() {
		List<Projekt> result;
		TypedQuery<Projekt> q = entityManager.createQuery(""+
				"From Projekt as p", Projekt.class);
		result = q.getResultList();
		return result;
	}

}
