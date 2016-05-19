package at.irian.jsfatwork.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.dao.ProjektDao;
import at.irian.jsfatwork.dao.ProjektDaoImpl;
import at.irian.jsfatwork.dao.StatusDaoImpl;
import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
import at.irian.jsfatwork.domain.Status;
@ManagedBean
public class StatusServiceImpl implements StatusService {
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
	public List<Status> showAllStati() {
		StatusDaoImpl sdi = new StatusDaoImpl();
		sdi.setEntityManager(this.entityManager);
		List<Status> result;
		this.entityManager.getTransaction().begin();
		result = sdi.findAll();
		this.entityManager.getTransaction().commit();
		return result;
	}

	public StatusServiceImpl() {
		this.entityManager = EntitySingleton.getEntitymanager();
	}


}
