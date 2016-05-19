package at.irian.jsfatwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;

public class StatusDaoImpl implements StatusDao {
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
	public List<Status> findAll() {
		List<Status> result;
		TypedQuery<Status> q = entityManager.createQuery(""+
				"From Status as s", Status.class);
		result = q.getResultList();
		return result;
	}

}
