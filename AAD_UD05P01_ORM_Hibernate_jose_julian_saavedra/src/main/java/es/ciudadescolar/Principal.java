package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br= new BufferedReader(isr);
		try {
			
			int numero;
			do {
				System.out.println("1- dar de alta un bar + detalle: ");
				System.out.println("2- modificar un bar: ");
				System.out.println("3- consultar un bar junto con su titular: ");
				System.out.println("4- borrar titular y bar en cascada: ");
				System.out.println("5- dar de alta un profesor + asiganturas");
				System.out.println("6- consultar un profesor y sus asiganturas");
				System.out.println("7- buscar una pelicula y sus actores");
				
				System.out.println("Introduce un numero para realizar una accion sobre la base de datos");
				numero=Integer.valueOf(br.readLine());
				
				switch(numero){
				case 1:
					break;
				case 2:
					break;
				case 5:
					break;
				case 6:
					break;
				}
				
			}while(numero>0&&numero<7);
			
		}catch(Exception e) {
			
		}
		
		LOGGER.info("Iniciando programa...");
		
		EntityManager em = emf.createEntityManager();
		
		
		
		emf.close();
		LOGGER.info("Fin del programa");
	}

}
