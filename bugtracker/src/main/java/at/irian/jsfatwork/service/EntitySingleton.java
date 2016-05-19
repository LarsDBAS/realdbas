package at.irian.jsfatwork.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntitySingleton {
	
	private static EntityManager entitymanager = null;
	
	
	public static EntityManager getEntitymanager(){
		if(EntitySingleton.entitymanager == null){
			EntitySingleton.entitymanager = Persistence.createEntityManagerFactory( "my-app").createEntityManager();
		}
		return EntitySingleton.entitymanager;
	}

}
