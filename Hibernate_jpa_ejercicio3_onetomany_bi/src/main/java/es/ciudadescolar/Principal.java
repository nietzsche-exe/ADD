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
		
		EntityManager em = emf.createEntityManager();
		
		Director dir1 = new Director();
		dir1.setCodigo_director(Integer.valueOf(1));
		dir1.setNombre_director("Paco Diaz");
		
		Pelicula peli1 = new Pelicula();
		peli1.setTitulo("Los bingueros");
		peli1.setFecha(LocalDate.of(1970, 4, 12));
		peli1.setDirector(dir1);
		
		Pelicula peli2 = new Pelicula();
		peli2.setTitulo("Los lunes al sol");
		peli2.setFecha(LocalDate.of(2005, 4, 12));
		peli2.setDirector(dir1);
		
		dir1.addPeliculaDirigida(peli1);
		dir1.addPeliculaDirigida(peli2);
		
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			
			trans.begin();
			
			em.persist(dir1);
			
			trans.commit();
			
		} catch (Exception e) {
			LOGGER.error("Error durante la operacion de persistencia: " + e.getMessage());
			trans.rollback();
			
		} finally {
			LOGGER.info("Cierre de EntityTransaction");
			em.close();
		}
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
		EntityManager em2 = emf.createEntityManager();
		
		Director dir2 = em2.find(Director.class, Integer.valueOf(1));
		
		if (dir2 == null) { 
			LOGGER.warn("Director no encontrado");
		}
		else {
			LOGGER.info(dir2.toString());
		}
		
		em2.close();
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
		
		EntityManager em3 = emf.createEntityManager();
		
		Director dir3 = em3.find(Director.class, Integer.valueOf(1));
		
		if (dir3 == null) { 
			LOGGER.warn("Director no encontrado");
		}
		else {
			EntityTransaction trans2 = em3.getTransaction();
			
			try {
				
				trans2.begin();
				
				em3.remove(dir3);
				
				trans2.commit();
				
			} catch (Exception e) {
				LOGGER.error("Error durante la operacion de persistencia: " + e.getMessage());
				trans2.rollback();	
			}
		}
	
		
		em3.close();
		
		LOGGER.info("Cierre de EntityManager");
		emf.close();
		
	}
}

