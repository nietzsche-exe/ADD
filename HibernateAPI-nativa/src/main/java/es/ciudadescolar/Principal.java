package es.ciudadescolar;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {

		Session sesion = sf.openSession();
		Pelicula peli = new Pelicula();
		peli.setTitulo("Alien");
		peli.setFecha(LocalDate.of(1980, 9, 20));
		// el objeto peli de la clase pelicula está en estado 'transient'. Es decir , en
		// la RAM

		// siempre que trabajemos con Hibernate lo haremos con transacciones.
		// sesion.getTransaction().begin();

		Transaction transaccion = sesion.getTransaction();
		try {
			transaccion.begin();

			LOGGER.info("Convirtiendo en persistente y administradol ainstancia peli de la clase Pelicula");
			sesion.persist(peli);// el objeto peli pasa en este momento a estar persistente y administrado
			peli.setTitulo("Alien IV");

			transaccion.commit();
			LOGGER.info("Commit realizado satisfactoriamente");

		} catch (Exception e) {

			transaccion.rollback();
			LOGGER.error("Error durante la gestión de la persistencia de las peliculas:" + e.getMessage());
		}

		finally {

			sesion.close();

		}

		Session sesion2 = sf.openSession();

		peli.setTitulo("Alien Resurrección");

		// esto no se puede hacer dado que peli es detached. Deberíamos hacer un merge
		// sesion2.persist(peli);

		if (!(sesion2.contains("es.ciudadescolarAPInativa.Pelicula", peli))) {

			LOGGER.info("La instancia peli no está actualmente administrada por la sesision2");

			Transaction transaccion2 = sesion2.getTransaction();

			try {
				transaccion2.begin();
				peli = sesion2.merge(peli);
				peli.setFecha(LocalDate.of(1981, 6, 12));

				transaccion2.commit();

			} catch (Exception e) {

				transaccion2.rollback();

			} finally {

				sesion2.close();
			}
		} else {

			LOGGER.info("La instancia peli YA está actualmente administrada por la sesion2");
		}

		Session sesion3 = sf.openSession();

		// Buscaremos una instancia persistente cuya PK sea una de las que tenemos en la
		// BD

		Integer idPeli = Integer.valueOf(12);

		// con la API Nativa de Hibernate en lugar de usar find() usaremos get()

		Pelicula peli2 = null;
		peli2 = sesion3.get(Pelicula.class, idPeli);

		if (peli2 == null) {

			LOGGER.warn("No se ha encontrado pelicula con id:" + idPeli);
		} else {

			// escenario: hemos encontrado la instancia y además la tenemos persistente

			Transaction transaccion3 = sesion3.getTransaction();

			try {

				transaccion3.begin();

				sesion3.remove(peli2);
				LOGGER.info("Borrada pelicula:" + peli2);

				transaccion3.commit();

			} catch (Exception e) {

				transaccion3.rollback();

			}

		}

		sesion3.close();

		sf.close();

		LOGGER.trace("Cierre correcto de la sesión");

		/*
		 * em = emf.createEntityManager();
		 * 
		 * // la instancia peli está en estado detached
		 * 
		 * peli.setTitulo("Los inmortales III");
		 * 
		 * // no puedo hacer que pase a persistente directamente desde estado detached
		 * // em.persist(peli);
		 * 
		 * // no puedo hacer que pase a persistente directamente desde estado detached
		 * // em.remove(peli);
		 * 
		 * em.getTransaction().begin();
		 * 
		 * try {
		 * 
		 * if(!em.contains(peli)) { // si no está siendo administrada la instancia peli
		 * en mi contexto de persistencia
		 * 
		 * em.merge(peli);
		 * 
		 * }
		 * 
		 * em.getTransaction().commit();
		 * 
		 * }catch (Exception e){
		 * 
		 * em.getTransaction().rollback(); } em.close();
		 * 
		 * em = emf.createEntityManager();
		 * 
		 * Pelicula peli2 = null;
		 * 
		 * //con el método find recupero de la BD una instancia administrada a partir de
		 * su ID (PK) peli2 = em.find(Pelicula.class, Integer.valueOf(6));
		 * 
		 * if (peli2==null) {
		 * 
		 * LOGGER.warn("No se ha encontrado la pelicula con ID 1"); } else {
		 * 
		 * LOGGER.info("pelicula con ID 1 encontrada:"+ peli2.toString());
		 * em.getTransaction().begin(); try {
		 * 
		 * em.remove(peli2); LOGGER.
		 * info("Borrada satisfactoriamente la pelicula recuperada con el find de BD");
		 * 
		 * em.getTransaction().commit();
		 * 
		 * }catch(Exception e){
		 * 
		 * LOGGER.error("Error al Borrar la pelicula");
		 * 
		 * em.getTransaction().rollback(); } } em.close();
		 * 
		 * 
		 * //modificar
		 * 
		 * em = emf.createEntityManager();
		 * 
		 * Pelicula peli3 = null;
		 * 
		 * //con el método find recupero de la BD una instancia administrada a partir de
		 * su ID (PK) peli3 = em.find(Pelicula.class, Integer.valueOf(9));
		 * 
		 * if (peli3==null) {
		 * 
		 * LOGGER.warn("No se ha encontrado la pelicula con ID 1"); } else {
		 * 
		 * LOGGER.info("pelicula con ID 1 encontrada:"+ peli3.toString());
		 * em.getTransaction().begin(); try {
		 * 
		 * peli3.setTitulo("Terminator"); LOGGER.
		 * info("Modificado satisfactoriamente la pelicula recuperada con el find de BD"
		 * );
		 * 
		 * em.getTransaction().commit();
		 * 
		 * }catch(Exception e){
		 * 
		 * 
		 * LOGGER.error("Error al modificar la pelicula");
		 * 
		 * em.getTransaction().rollback(); } } em.close();
		 * 
		 * 
		 * emf.close(); LOGGER.trace("Cierre correcto del entitymanagerfactory");
		 * 
		 * LOGGER.info("Fin de nuestra primera aplicación de ORM Hibernate");
		 * 
		 * }
		 */
	}
}