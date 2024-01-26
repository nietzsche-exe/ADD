package es.ciudadescolar;

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
		
		Direccion direccion = new Direccion();
		direccion.setCalle("calle desconocida");
		direccion.setNumero(Integer.valueOf(65));
		LOGGER.info("Creada la instancia direccion");
		
		Actor actor = new Actor();
		actor.setNombre("Tom Hanks");
		actor.setDireccion_actor(direccion);
		LOGGER.info("Creada instancia de actor");
		
		direccion.setActor(actor);
		
		EntityTransaction transaccion = em.getTransaction();
		
		try {
			transaccion.begin();
			
			em.persist(direccion);
			
			transaccion.commit();
		}
		catch(Exception e) {
			transaccion.rollback();
			LOGGER.error("Error durante la operacion de persistencia: " + e.getMessage());
		}
		finally {
			em.close();
			LOGGER.info("EntityManager cerrado");
		}
		
		EntityManager em2 = emf.createEntityManager();
		Integer idDireccion = Integer.valueOf(5);
		
		Direccion direccionBuscada = em2.find(Direccion.class, Integer.valueOf(idDireccion));
		
		if(direccionBuscada == null) {
			LOGGER.warn("No se a encontrado al actor: ");
		}
		else {
			LOGGER.info("Direccion recuperada: " + direccionBuscada.getCalle());
			
			EntityTransaction transaccion2 = em2.getTransaction();
			
			try {
				transaccion2.begin();
				
				em2.remove(direccionBuscada);
				LOGGER.info("Direccion con id [" + idDireccion + "] borrada");
				
				transaccion2.commit();
			}
			catch(Exception e) {
				transaccion2.rollback();
				LOGGER.error("Error en la operacion de borrado: " + e.getMessage());
			}
			finally {
				em2.close();
				LOGGER.info("EntityManager cerrado");
			}
			
		}
		
		em2.close();
		
		
		
		emf.close();
		LOGGER.info("Fin del programa");
	}

}
