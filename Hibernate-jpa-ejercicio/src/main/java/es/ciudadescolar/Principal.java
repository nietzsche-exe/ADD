package es.ciudadescolar;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class); 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManager em = emf.createEntityManager();
		
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Los inmortales");
		pelicula.setFecha(LocalDate.of(1980, 9, 20));
		//la pelicula esta en estado transient
		
		//siempre se trabaja en transaccion con hibernate
		em.getTransaction().begin();
		
		try {
		em.persist(pelicula); //objeto en estado persistente
		
		em.getTransaction().commit(); 
		//fin de la transaccion
		}
		catch(Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Error durante la persistencia de la pelicula" + e.getMessage());
		}
		finally {
			em.close();
			LOGGER.trace("Cierre correcto del entitymanager");
		}
		
		
		em = emf.createEntityManager();

		// la instancia peli está en estado detached

		pelicula.setTitulo("Sidonia");

		// no puedo hacer que pase a persistente directamente desde estado detached
		// em.persist(peli);

		// no puedo hacer que pase a persistente directamente desde estado detached
		// em.remove(peli);

		em.getTransaction().begin();

		try {

			if (!em.contains(pelicula)) { // si no está siendo administrada la instancia peli en mi contexto de
											// persistencia
				em.merge(pelicula);

			}
			
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
		

		em = emf.createEntityManager();

		Pelicula peli2 = null;

		// con el método find recupero de la BD una instancia administrada a partir de
		// su ID (PK)
		peli2 = em.find(Pelicula.class, Integer.valueOf(6));

		if (peli2 == null) {

			LOGGER.warn("No se ha encontrado la pelicula con ID 6");
		} else {

			LOGGER.info("pelicula con ID 6 encontrada:" + peli2.toString());
			em.getTransaction().begin();
			try {

				em.remove(peli2);
				LOGGER.info("Borrada satisfactoriamente la pelicula recuperada con el find de BD");

				em.getTransaction().commit();

			} catch (Exception e) {

				LOGGER.error("Error al Borrar la pelicula");

				em.getTransaction().rollback();
			}
		}
		em.close();

		// modificar

		em = emf.createEntityManager();

		Pelicula peli3 = null;

		// con el método find recupero de la BD una instancia administrada a partir de
		// su ID (PK)
		peli3 = em.find(Pelicula.class, Integer.valueOf(9));

		if (peli3 == null) {

			LOGGER.warn("No se ha encontrado la pelicula con ID 9");
		} else {

			LOGGER.info("pelicula con ID 9 encontrada:" + peli3.toString());
			em.getTransaction().begin();
			try {

				peli3.setTitulo("Terminator");
				LOGGER.info("Modificado satisfactoriamente la pelicula recuperada con el find de BD");

				em.getTransaction().commit();

			} catch (Exception e) {

				LOGGER.error("Error al modificar la pelicula");

				em.getTransaction().rollback();
			}
		}
		em.close();

		emf.close();
		LOGGER.trace("Cierre correcto del entitymanagerfactory");

		LOGGER.info("Fin de nuestra primera aplicación de ORM Hibernate");

	}

}
