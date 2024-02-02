package es.JoseJulianSaavedra;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/*
 * Clase Principal del programa que ejecuta la logica principal
 * @author Jose Julian Saavedra
 * @version 1.0 
 */
public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaAgencia");
	
	
	/*
	 * Metodo main. En el tenemos los metodos darAltaTurista(), listarTuristas(), modificarFecha(), borrarTurista()
	 * @params no recibe ningun parametro
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LOGGER.info("Inicio del Programa");
		
		/*
		El metodo darAltaTurista() no funciona correctamente, 
		lo he dejado comentado para que no te salte ningun error al ejecutar el programa, 
		al no poder insertar esos registros el resto de metodos funcionan con otros registros por defectos de la base de datos
		*/
		
		//darAltaTurista();
		listarTuristas();
		modificarFecha(1, LocalDate.of(2009, 12, 19));
		borrarTurista(3);
		
		emf.close();
		LOGGER.info("Fin del Programa");
		
		
	}
	
	/*
	 * Este metodo crea distintas instancias de Turista y Reserva para ser insertados en la base de datos
	 */
	public static void darAltaTurista() {
		
		EntityManager em = emf.createEntityManager();
		
		Turista turista1 = new Turista();
		turista1.setCodigo_turista(3001);
		turista1.setNombre_turista("Domingo");
		turista1.setApellido_turista("Pazos");
		turista1.setFecha_turista(LocalDate.of(2005, 6, 15));
		LOGGER.info("Instancia creada de Turista: " + turista1);
		
		Turista turista2 = new Turista();
		turista2.setCodigo_turista(3002);
		turista2.setNombre_turista("Mario");
		turista2.setApellido_turista("García");
		turista2.setFecha_turista(LocalDate.of(1999, 2, 21));
		LOGGER.info("Instancia creada de Turista: " + turista2);
		
		Reserva reserva1 = new Reserva();
		reserva1.setHotel_reserva("Hotel Mediterraneo");
		reserva1.setFecha_entrada(LocalDate.now());
		reserva1.setFecha_salida(reserva1.getFecha_entrada().plusWeeks(1));
		LOGGER.info("Instancia creada de Reserva: " + reserva1);
		
		
		Reserva reserva2 = new Reserva();
		reserva2.setHotel_reserva("Hotel Odisea");
		reserva2.setFecha_entrada(LocalDate.now());
		reserva2.setFecha_salida(reserva1.getFecha_entrada().plusWeeks(1));
		LOGGER.info("Instancia creada de Reserva: " + reserva2);
		
		
		
		EntityTransaction transaccion = em.getTransaction();
			
		try {
			
			transaccion.begin();
			
			em.persist(turista1);
			em.persist(turista2);
			
			transaccion.commit();
			
			turista1.addReserva(reserva1);
			turista2.addReserva(reserva2);
			
			transaccion.begin();
			
			em.persist(reserva1);
			em.persist(reserva2);
			
			transaccion.commit();
			
			
		}
		catch(Exception e) {
			transaccion.rollback();
			LOGGER.error("Error en la operacion de persistencia del turista: " + turista1);
			e.printStackTrace();
		}
		finally {
			em.close();
			LOGGER.info("Cerrado el EntityManager");
		}
		
	}
	
	/*
	 * Este Metodo modifica la fecha de nacimiento de un turista
	 * @params se recibe como parametro el id del turista y la nueva fecha
	 */
	public static void modificarFecha(Integer idTurista, LocalDate fechaNacimiento) {
		
		EntityManager em = emf.createEntityManager();
		
		Turista turista = em.find(Turista.class, idTurista);
		
		turista.setFecha_turista(fechaNacimiento);
		
		EntityTransaction transaccion =  em.getTransaction();
		
		try {
			
		transaccion.begin();
		
		em.merge(turista);
		
		transaccion.commit();
		
		}
		catch(Exception e) {
			transaccion.rollback();
			LOGGER.error("Error en la persistencia del turista: " + turista);
		}
		finally {
			em.close();
			LOGGER.info("Cerrado el EntityManager");;
		}
	}
	
	/*
	 * Este metodo borra un determinado turista
	 * @params recibe como parametro el id del turista 
	 */
	public static void borrarTurista(Integer idTurista) {
		
		EntityManager em = emf.createEntityManager();
		
		Turista turista = em.find(Turista.class, idTurista);
		
		EntityTransaction transaccion =  em.getTransaction();
		
		try {
			
		transaccion.begin();
		
		em.remove(turista);
		
		transaccion.commit();
		
		}
		catch(Exception e) {
			transaccion.rollback();
			LOGGER.error("Error en la persistencia del turista: " + turista);
		}
		finally {
			em.close();
			LOGGER.info("Cerrado el EntityManager");;
		}
	}
	
	/*
	 * Este Metodo lista todos los registros de turistas en la base de datos
	 * @params no recibe parametros
	 */
	public static void listarTuristas() {
				
		EntityManager em = emf.createEntityManager();

		Query consultarTuristas = em.createQuery("from Turista");
		
		List<Turista> listaTuristas = consultarTuristas.getResultList();

		for (Turista turista : listaTuristas) {
			LOGGER.info(turista.toString());
		}
		
	}
	

}
