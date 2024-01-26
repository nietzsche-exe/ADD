package es.ciudadescolar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		EntityManager em = emf.createEntityManager();
		
		
		Query consultarActor = em.createQuery("from Actor a where a.nombre = :name");
		consultarActor.setParameter("name", "Carlos");
		List<Actor> listaActores = consultarActor.getResultList();
		
		for (Actor actor : listaActores) {
			LOGGER.info(actor.toString());
		}
		
		
		
		emf.close();
		LOGGER.info("Fin del programa");
	}

}
