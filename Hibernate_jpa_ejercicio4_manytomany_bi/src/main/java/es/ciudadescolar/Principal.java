package es.ciudadescolar;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class); 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LOGGER.info("Iniciando programa...");
		
		EntityManager em = emf.createEntityManager();
		
		
		Actor actor = new Actor("Jon", new Direccion("San Cristobal", 7));
		
		Pelicula peli = new Pelicula("Como entrenar a tu dragon", LocalDate.of(2006, 8, 23));
		
		Pelicula peli2 = new Pelicula("Vikingos", LocalDate.of(2009, 2, 15));
		
		actor.addPelicula(peli);
		actor.addPelicula(peli2);
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.persist(actor);
			
			trans.commit();
		}
		catch(Exception e) {
			trans.rollback();
			LOGGER.error("Error en la transaccion");
		}
		finally {
			em.close();
		}
		
		emf.close();
		LOGGER.info("Fin del programa");
	}

}
