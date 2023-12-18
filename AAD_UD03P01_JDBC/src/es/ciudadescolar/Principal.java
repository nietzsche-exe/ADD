package es.ciudadescolar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Principal 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);
	
	public static void main(String[] args) 
	{
		
		List<Pelicula> pelis = GestorPeliculas.leerFichero("peliculas.txt");
		if (pelis.size() == 0)
		{
			LOGGER.error("Error, no se han recuperado ninguna película del fichero");
			System.exit(1);
		}
		
		BBDD bd = null;
		
		try {
			
				bd = new BBDD();
				
				for (Pelicula pelicula:pelis)
				{
					bd.insertarPelicula(pelicula);
					LOGGER.trace("Pelicula procesada ["+pelicula.getTitulo()+"]");
				}

				Scanner sc = new Scanner(System.in);
				System.out.println("Itroduce nombre del actor a consultar:");
				String nomActor = sc.nextLine();
				sc.close();				
				List<Pelicula> pelisActor = bd.buscarPeliculasPorActor(nomActor);
				
				LOGGER.info("El actor ["+nomActor+"] ha participado en ["+pelisActor.size()+ "] peliculas");
				int i =1;
				for (Pelicula peli:pelisActor)
				{
					LOGGER.info("  "+ i+")" + peli.getTitulo() + " con un total de [" + bd.getNumActoresPorPeliculaFUN(peli.getTitulo()) + "] actores");
					i++;
				}
		} 
		catch (ClassNotFoundException e) 
		{
			LOGGER.error("Ha habido un error durante el registro del driver Mysql");
			
		} catch (SQLException e) 
		{
			LOGGER.error("Ha habido un error durante la interacción con la BD");
		}
		
		catch (FileNotFoundException e) 
		{
			LOGGER.error("Ha habido un error durante la carga del fichero properties: No encontrado");
		} 
		catch (IOException e) 
		{
			LOGGER.error("Ha habido un error durante la carga del fichero properties");
		}
		finally
		{
			if (bd != null)
				bd.cierre();
		}
		
		
	}

}
