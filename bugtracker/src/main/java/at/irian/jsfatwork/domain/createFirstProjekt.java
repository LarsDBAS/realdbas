package at.irian.jsfatwork.domain;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.irian.jsfatwork.service.EntitySingleton;
import at.irian.jsfatwork.service.FehlerServiceImpl;
import at.irian.jsfatwork.service.ProjektService;
import at.irian.jsfatwork.service.ProjektServiceImpl;

public class createFirstProjekt {

    public static void main( String[ ] args ) 
    {
//		  EntityManagerFactory emfactory = Persistence.
//	      createEntityManagerFactory( "my-app" );
//	      EntityManager entitymanager = emfactory.
//	      createEntityManager();
//	      
	      Projekt p = new Projekt();    	    
	      ProjektServiceImpl ps = new ProjektServiceImpl();
	      ps.setEntityManager(EntitySingleton.getEntitymanager());
	      p.setProNr(1);
	      p.setBezeichnung("Erstes Projekt");
	      EntitySingleton.getEntitymanager().getTransaction().begin();
	      EntitySingleton.getEntitymanager().persist(p);
	      EntitySingleton.getEntitymanager().getTransaction().commit();

//	      ProjektServiceImpl psi = new ProjektServiceImpl();
//	      Fehler f = new Fehler(psi.getProjektByNr(1));
//	      f.setBeschreibung("Blabla");
//	      f.setBezeichnung("Erster Fehler");
//	      f.setStatus("angelegt");
//	      f.setPrioritaet(1);
//	      f.setErstellt(new Date(System.currentTimeMillis()));
//	      FehlerServiceImpl fs = new FehlerServiceImpl();
//	      fs.save(f);
    	

    	      
    	      
    	      
    }

}
