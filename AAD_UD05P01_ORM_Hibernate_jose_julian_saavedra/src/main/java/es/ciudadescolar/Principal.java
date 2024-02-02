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
import jakarta.persistence.Query;

public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class); 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LOGGER.info("Iniciando programa...");
		
		LOGGER.info("Relaciones 1:1");
		//darAltaClienteDetalles();
		//consultarClienteDetalles(3);
		
		
		LOGGER.info("Relaciones 1:N");
		//darAltaClientePagos();
		//consultarClientePagos(2);

		LOGGER.info("Relaciones N:M");
		//darAltaProducto();
		//consultarProducto(1);
		
		LOGGER.info("Consultas HQL:");
		//consultaClienteHQL("Jane");
		//modificacionPagoHQL("John", "Doe", LocalDate.of(2023, 01, 25), 1099.97);
		//deleteProductoHQL("Los pilares de la tierra");
		
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
			
			cliente.getCliente_detalles().setTelefono("149656899");
			
			transaccion.commit();
			
			transaccion.begin();
			
			em.remove(em.find(ClienteDetalles.class, cliente_detalles.getCod_cliente()));
			em.remove(em.find(Cliente.class, cliente.getCod_cliente()));
			
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
	        
	        
	        try {
	            
	        Cliente clienteBuscado = em.find(Cliente.class, idCustomer);
	        LOGGER.info("Se ha encontrado al cliente con id: " + idCustomer + "\n"
	        			+ "Cliente: " + clienteBuscado.toString());
	        	
	        } finally {
	            em.close(); 
	        }
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
		
		
		cliente.addPagos(pago1);
		cliente.addPagos(pago2);
		cliente.addPagos(pago3);
		
		
		EntityTransaction transaccion = em.getTransaction();
		
		try {
			
			transaccion.begin();
			
			em.persist(cliente);
			
			transaccion.commit();
			
			transaccion.begin();
			
			Pago pagoBuscado = em.find(Pago.class, cliente.getPagos().get(2));
			em.remove(pagoBuscado);
			
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
	
	public static void consultarClientePagos(Integer idCustomer) {
		
        EntityManager em = emf.createEntityManager();
        
        
        try {
            
        Cliente clienteBuscado = em.find(Cliente.class, idCustomer);
        LOGGER.info("Se ha encontrado al cliente con id: " + idCustomer + "\n"
        			+ "Cliente: " + clienteBuscado.toString());
        	
        } finally {
            em.close(); 
        }
	}
	
	public static void darAltaProducto() {
		
		EntityManager em = emf.createEntityManager();
	
		Producto producto = new Producto();
		producto.setNombre("Los pilares de la tierra");
		producto.setPrice(21.99);
		
		Categoria categoria = new Categoria();
		categoria.setNombre("libros");
		Categoria categoria2 = new Categoria();
		categoria2.setNombre("entretenimiento");
		
		producto.addCategoria(categoria);
		producto.addCategoria(categoria2);
		
		EntityTransaction transaccion = em.getTransaction();
		
		try {
			
			transaccion.begin();
			
			em.persist(producto);
			
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

	public static void consultarProducto(Integer idProducto) {

		EntityManager em = emf.createEntityManager();

		try {

			Producto productoBuscado = em.find(Producto.class, idProducto);
			LOGGER.info("Se ha encontrado el Producto con id: " + idProducto + "\n" + "Cliente: "
					+ productoBuscado.toString());

		} finally {
			em.close();
		}
	}
	
	public static void consultaClienteHQL(String nombre) {
		
		EntityManager em = emf.createEntityManager();
		
		Query consultarCliente = em.createQuery("from Cliente a where a.nombre = :name");
		consultarCliente.setParameter("name", nombre);
		List<Cliente> listaClientes = consultarCliente.getResultList();
		
		for (Cliente cliente : listaClientes) {
			LOGGER.info(cliente.toString());
		}
		
	}
	
	public static void modificacionPagoHQL(String nombre, String appellido, LocalDate fecha, Double importe) {
	    EntityManager em = emf.createEntityManager();

	    Query consultarCliente = em.createQuery("from Cliente c where c.nombre = :name and c.appellido = :apellido");
	    consultarCliente.setParameter("name", nombre);
	    consultarCliente.setParameter("apellido", appellido);
	    Cliente cliente = (Cliente) consultarCliente.getSingleResult();

	    Query consultarPago = em.createQuery("from Pago p where p.cliente = :cliente and p.fecha = :fecha");
	    consultarPago.setParameter("cliente", cliente);
	    consultarPago.setParameter("fecha", fecha);
	    Pago pago = (Pago) consultarPago.getSingleResult();

	    pago.setCantidad(importe);

	    em.getTransaction().begin();
	    em.merge(pago);
	    em.getTransaction().commit();

	    em.close();
	}
	
	public static void deleteProductoHQL(String nombre) {
	    EntityManager em = emf.createEntityManager();

	    Query consultaProducto = em.createQuery("from Producto p where p.nombre = :nombre");
	    consultaProducto.setParameter("nombre", nombre);
	    Producto producto = (Producto) consultaProducto.getSingleResult();

	    em.getTransaction().begin();
	    em.remove(producto);
	    em.getTransaction().commit();

	    em.close();
	}
	

}
