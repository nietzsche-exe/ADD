package es.ciudadescolar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		
		LOGGER.info("Relaciones 1:1");
		//darAltaClienteDetalles();
		//consultarClienteDetalles(3);
		//eliminarCliente();
		
		LOGGER.info("Relaciones 1:N");
		darAltaClientePagos();
		
		emf.close();
		LOGGER.info("Fin del programa");
	}
	
	public static void darAltaClienteDetalles() {
		
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = new Cliente();
		cliente.setEmail("pd1921@tocomocho.net");
		cliente.setNombre("Paco");
		cliente.setAppellido("Díaz");
		
		ClienteDetalles cliente_detalles = new ClienteDetalles();
		cliente_detalles.setTelefono("6496568991");
		cliente_detalles.setDireccion("Avda. Sin nombre, 121");

	
		
		EntityTransaction transaccion = em.getTransaction();
		
		try {
			
			transaccion.begin();
			
			em.persist(cliente);
			
			transaccion.commit();
			
			cliente_detalles.setCod_cliente(cliente.getCod_cliente());
			cliente.setCliente_detalles(cliente_detalles);
			
			transaccion.begin();
			
			em.persist(cliente_detalles);
			
			transaccion.commit();
			
			transaccion.begin();
			
			Cliente clienteBuscado = em.find(Cliente.class, cliente_detalles.getCod_cliente());
			clienteBuscado.getCliente_detalles().setTelefono("149656899");
			
			transaccion.commit();
			
			
		}
		catch(Exception e) {
			transaccion.rollback();
			LOGGER.error("Error durante la operacion de persistencia: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			em.close();
			LOGGER.info("EntityManager cerrado");
		}
		
	}
	
	public static void consultarClienteDetalles(Integer idCustomer) {
		
	        EntityManager em = emf.createEntityManager();
	        Integer customerId = 3;
	        
	        try {
	            
	        Cliente clienteBuscado = em.find(Cliente.class, customerId);
	        LOGGER.info("Se ha encontrado al cliente con id: " + customerId + "\n"
	        			+ "Cliente: " + clienteBuscado.toString());
	        	
	        } finally {
	            em.close(); 
	        }
	}
	
	
	
	public static void eliminarCliente() {
		
	}
	
	public static void darAltaClientePagos() {

		EntityManager em = emf.createEntityManager();

		Cliente cliente = new Cliente();
		cliente.setEmail("joaqrom@tocomocho.net");
		cliente.setNombre("Joaquín");
		cliente.setAppellido("Róman");
		
		Pago pago1 = new Pago();
		pago1.setCantidad(234.45);
		pago1.setFecha(LocalDate.of(2024, 01, 07));
		Pago pago2 = new Pago();
		pago2.setCantidad(100.25);
		pago2.setFecha(LocalDate.of(2024, 01, 12));
		Pago pago3 = new Pago();
		pago3.setCantidad(655.99);
		pago3.setFecha(LocalDate.of(2024, 01, 17));
		
		
		
		cliente.setPagos();
		
		EntityTransaction transaccion = em.getTransaction();
		
		try {
			
			transaccion.begin();
			
			em.persist(cliente);
			
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
		
	}
	
	

}
