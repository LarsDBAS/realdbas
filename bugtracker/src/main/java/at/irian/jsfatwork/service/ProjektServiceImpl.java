package at.irian.jsfatwork.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import at.irian.jsfatwork.dao.ProjektDao;
import at.irian.jsfatwork.dao.ProjektDaoImpl;
import at.irian.jsfatwork.domain.Fehler;
import at.irian.jsfatwork.domain.Projekt;
@ManagedBean
public class ProjektServiceImpl implements ProjektService {
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
	public void save(Projekt projekt) {
		ProjektDaoImpl pdao = new ProjektDaoImpl();
		pdao.setEntityManager(this.entityManager);
		pdao.getEntityManager().getTransaction().begin();
		pdao.makePersistant(projekt);
		pdao.getEntityManager().getTransaction().commit();
		
	}

	@Override
	public void delete(Projekt projekt) {
		ProjektDaoImpl pdao = new ProjektDaoImpl();
		pdao.setEntityManager(this.entityManager);
		pdao.getEntityManager().getTransaction().begin();
		pdao.makeTransient(projekt);
		pdao.getEntityManager().getTransaction().commit();
		
	}

	@Override
	public List<Projekt> showAllProjects() {
		ProjektDaoImpl pdi = new ProjektDaoImpl();
		pdi.setEntityManager(this.entityManager);
		List<Projekt> result;
		this.entityManager.getTransaction().begin();
		result = pdi.findAll();
		this.entityManager.getTransaction().commit();
		return result;
	}

	public ProjektServiceImpl() {
		this.entityManager = EntitySingleton.getEntitymanager();
	}

	@Override
	public Projekt getProjektByNr(int proNr) {
		ProjektDaoImpl pdi = new ProjektDaoImpl();
		pdi.setEntityManager(this.entityManager);
		Projekt result;
		this.entityManager.getTransaction().begin();
		result = pdi.findByNr(proNr);
		this.entityManager.getTransaction().commit();
		return result;
	}

}
