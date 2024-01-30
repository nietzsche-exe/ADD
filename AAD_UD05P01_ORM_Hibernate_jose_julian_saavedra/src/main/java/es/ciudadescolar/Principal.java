package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class); 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LOGGER.info("Iniciando programa...");
		
		//darAltaCliente();
		consultarCliente();
		
		emf.close();
		LOGGER.info("Fin del programa");
	}
	
	public static void darAltaCliente() {
		
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = new Cliente();
		cliente.setEmail("pd1921@tocomocho.net");
		cliente.setNombre("Paco");
		cliente.setAppellido("Díaz");
		
		ClienteDetalles cliente_detalles = new ClienteDetalles();
		cliente_detalles.setTelefono("6496568991");
		cliente_detalles.setDireccion("Avda. Sin nombre, 121");
		
		cliente.setCliente_detalles(cliente_detalles);
		cliente_detalles.setCliente(cliente);
		
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
	
	public static void consultarCliente() {
		
	        EntityManager em = emf.createEntityManager();
	        Integer customerId = 3;
	        
	        try {
	            
	        	String hql = "FROM Cliente c WHERE c.customer_id = :customer_id";
	            Query query = em.createQuery(hql);
	            query.setParameter("customer_id", customerId);

	            List<Cliente> resultados = query.getResultList();

	            if (!resultados.isEmpty()) {
	               
	            	for (Cliente cliente : resultados) {
						System.out.println(cliente);
					}
	            	
	            } else {
	               LOGGER.error("no se encontro al cliente con id: " + customerId);
	                
	            }
	        } finally {
	            em.close(); 
	        }
	}

}
