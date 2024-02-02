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

/*
 * Clase Principal. En esta clase se ejecuta la logica principal del programa
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since 2023 - 2024
 */
public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class); 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaPeliculas");
	
	/*
	 * Metodo Main. En este metodo se ejecutan los metodos darAltaCliente(), consultarClienteDetalles(), darAltaClientePagos(), consultarClientePagos(), darAltaProducto(), consultarProducto(), consultaClienteHQL(), modificacionPagoHQL() y deleteProductoHQL()  
	 * @author Jose Julian Saavedra
     * @version 1.0
 	 * @since 2023 - 2024
 	 * @params no recibe parametros
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LOGGER.info("Iniciando programa...");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {

			int numero;
			do {
				System.out.println("1- Dar alta cliente con detalles (El cliente que se crea se borrara automaticmente, para ver su informacion vea el log)");
				System.out.println("2- Consultar cliente con detalles (Se consulta el cliente con id = 3)");
				System.out.println("3- Dar alta cliente con pagos");
				System.out.println("4- Consultar cliente con pagos");
				System.out.println("5- Dar alta producto (Se dara de alta el producto: Los pilares de la tierra)");
				System.out.println("6- Consultar producto (Se consulta el producto con id = 1)");
				System.out.println("7- Consultar cliente con HQL");
				System.out.println("8- Modificar pago con HQL (se modifica el pago del cliente John Doe cuya fecha es 2023/01/25)");
				System.out.println("9- Borrar producto con HQL (Se borran todos los productos: Los pilares de la tierra)");
				System.out.println("10- Salir");

				System.out.println("Introduce un numero para realizar una accion sobre la base de datos");
				numero = Integer.valueOf(br.readLine());

				switch (numero) {
				case 1:
					darAltaClienteDetalles();
					break;
				case 2:
					consultarClienteDetalles(3);
					break;
				case 3:
					darAltaClientePagos();
					break;
				case 4:
					consultarClientePagos(2);
					break;
				case 5:
					darAltaProducto();
					break;
				case 6:
					consultarProducto(1);
					break;
				case 7:
					consultaClienteHQL("Jane");
					break;
				case 8:
					modificacionPagoHQL("John", "Doe", LocalDate.of(2023, 01, 25), 988.97);
					break;
				case 9:
					deleteProductoHQL("Los pilares de la tierra");
					break;
					

				}

			} while (numero > 0 && numero < 9);

		} catch (Exception e) {
			LOGGER.error("Execepcion en el menu principal");
		}
		
		emf.close();
		LOGGER.info("Fin del programa");
	}
	
	/*
	 * Este Metodo crea instancias de la clase Cliente y ClienteDetalles para posteriormente ser insertados en la base de datos, luego modifica el numero de telefono del cliente y al finalizar borra el cliente recien creado
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params no recibe parametros
	 */
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
	
	/*
	 * Este Metodo vuelca al log la informacion de un cliente determinado 
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el id del cliente que quiera consultar
	 */
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
	
	/*
	 * Este Metodo crea instancias de las clases Cliente y Pago para ser insertados en la base de datos 
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params no recibe parametros
	 */
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
			
			Pago pagoBuscado = em.find(Pago.class, cliente.getPagos().get(2).getCod_pagos());
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
	
	/*
	 * Este Metodo vuelca al log la informacion de un cliente determinado junto con sus pagos
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el id del cliente que quiera consultar 
	 */
	public static void consultarClientePagos(Integer idCustomer) {
		
        EntityManager em = emf.createEntityManager();
        
        
        try {
            
        Cliente clienteBuscado = em.find(Cliente.class, idCustomer);
        LOGGER.info("Se ha encontrado al cliente con id: " + idCustomer + ", Cliente: " + clienteBuscado.getNombre());
        for (int i = 0; i < clienteBuscado.getPagos().size(); i++) {
        	LOGGER.info("Pagos: " + clienteBuscado.getPagos().get(i));
        }
        
        } finally {
            em.close(); 
        }
	}
	
	/*
	 * Este Metodo crea instancias de las clases Producto y Categoria para ser insertados en la base de datos 
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params no recibe parametros
	 */
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

	/*
	 * Este Metodo vuelca al log la informacion de un producto determinado junto con sus categorias
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el id del producto que quiera consultar 
	 */
	public static void consultarProducto(Integer idProducto) {

		EntityManager em = emf.createEntityManager();

		try {

			Producto productoBuscado = em.find(Producto.class, idProducto);
			LOGGER.info("Se ha encontrado el Producto con id: " + idProducto + ", Producto: " + productoBuscado.getNombre());

			for(int i = 0; i < productoBuscado.getCategorias().size(); i++) {
				LOGGER.info("Categoria: " + productoBuscado.getCategorias().get(i));
			}
			
			
		} finally {
			em.close();
		}
	}
	
	/*
	 * Este Metodo vuelca al log la informacion el nombre de un cliente y sus pagos mediante el uso de HQL
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el id del cliente que quiera consultar 
	 */
	public static void consultaClienteHQL(String nombre) {
		
		EntityManager em = emf.createEntityManager();
		
		Query consultarCliente = em.createQuery("select c.nombre, p.cantidad from Cliente c, Pago p where c.nombre = :name AND p.cliente.cod_cliente = c.cod_cliente");
		
		consultarCliente.setParameter("name", nombre);
		List<?> listaClientes = consultarCliente.getResultList();
		
		for (int i = 0; i < listaClientes.size(); i++) {
			Object[] objetos = (Object[]) listaClientes.get(i);
			LOGGER.info( "Nombre: " + objetos[0].toString() + ", Pagos: " + objetos[1].toString());
		}
		
	}
	
	/*
	 * Este Metodo modifica un pago determinado en base a los parametros que recibe el metodo
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el nombre y apellido del cliente ademas de la fecha del pago a modificar y su nuevo valor
	 */
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
	
	/*
	 * Este Metodo elimina de la base de datos un producto junto con sus categorias
	 * @author Jose Julian Saavedra
     * @version 1.0
	 * @since 2023 - 2024
	 * @params Recibe como parametros el nombre del producto 
	 */
	public static void deleteProductoHQL(String nombre) {
	    EntityManager em = emf.createEntityManager();

	    em.getTransaction().begin();
	    
	    Query consultaProducto = em.createQuery("delete from Producto p where p.nombre = :nombre");
	    consultaProducto.setParameter("nombre", nombre);
	    
	    int resultado = consultaProducto.executeUpdate();
	    
	    em.getTransaction().commit();
	   
	    em.close();
	}
	

}
