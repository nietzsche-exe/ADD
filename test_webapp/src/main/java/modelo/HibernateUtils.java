package modelo;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtils {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");

	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	
	
}
