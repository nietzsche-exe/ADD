package AAD_UD03P01_JDBC;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Principal. En ella se ejecuta el programa principal El programa crea e
 * inserta en una base de datos diferentes datos leidos de un fichero de texto
 * sobre peliculas
 * 
 * @author Jose Julian Saavedra
 * @version 1.0
 * @since curso 2023-2024
 */
public class Principal {

	private static final Logger LOGGER = LoggerFactory.getLogger(Principal.class);

	private static List<Peliculas> peliculas = new ArrayList<>();
	private static List<String> actores = new ArrayList<>();

	/**
	 * Metodo main. En el se ejecutan todos los metodos pertinentes para la el
	 * correcto funcionamiento del programa
	 * 
	 * @param args Recibe un fichero de texto
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LOGGER.debug("Inicio del programa");
		
		BBDD bd = null;
		File fichero = new File(args[0]);

		try {

			bd = new BBDD();

			leerFicheroPeliculas(fichero);

			for (int i = 0; i < actores.size(); i++) {
				bd.insertarActor(actores.get(i));
			}

			for (int i = 0; i < peliculas.size(); i++) {
				bd.insertarPelicula(peliculas.get(i).getTitulo(), peliculas.get(i).getAño());
			}

			/*
			for (int i = 0; i < peliculas.size() + actores.size(); i++) {
				boolean prota = false;
				if(peliculas.get(i).getActores()[i].equals(peliculas.get(i).getProtagonista().get(i))) {
					prota = true;
				}
				bd.insertarInterprete(peliculas.get(i).getActores()[i], peliculas.get(i).getTitulo(), prota);
			}
			*/
		
			
			for (int i = 0; i < peliculas.size(); i++) {
				for(int j = 0; j < peliculas.get(i).getActores()[j].length(); j++) {
					boolean prota = false;
					if(peliculas.get(i).getActores()[j].equals(peliculas.get(i).getProtagonista().get(j))) {
						prota = true;
					}
					bd.insertarInterprete(peliculas.get(i).getActores()[j], peliculas.get(i).getTitulo(), prota);
				}
				
			}
			
			
			for (int i = 0; i < peliculas.size(); i++) {
				bd.registrarNumeroActoresPorPelicula(peliculas.get(i).getTitulo());
			}

			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese el nombre del actor para buscar películas: ");
			String actorBuscado = scanner.nextLine();

			List<String> peliculasActor = bd.buscarPeliculasPorActor(actorBuscado);
			if (!peliculasActor.isEmpty()) {
				System.out.println("Películas en las que participó " + actorBuscado + ":");
				for (String pelicula : peliculasActor) {
					System.out.println("- " + pelicula);
				}
			} else {
				System.out.println(actorBuscado + " no participó en ninguna película según la base de datos.");
			}
			
			scanner.close();

		} catch (ClassNotFoundException e) {
			LOGGER.error("Ha habido un error durante el regsitro del driver mysql");
		} catch (SQLException e) {
			LOGGER.error("Ha habido un error durante la interaccion con la BD");
		} catch (FileNotFoundException e) {
			LOGGER.error("Fichero de propiedades no encontrado");
		} catch (IOException e) {
			LOGGER.error("Error de E/S");
		} finally {

			if (bd != null)
				bd.cerrarBD();
			
			LOGGER.debug("Fin del programa");
			
		}

	

	}

	/**
	 * Metodo leerFicheroPeliculas. Este metodo se encarga de leer los datos del
	 * fcihero de texto En el se instancian objetos Peliculas y se guardan en una
	 * coleccion dinamica
	 * 
	 * @param fichero Recibe el fichero de texto pasado al main por parametros
	 * @author Jose Julian Saavedra
	 * @version 1.0
	 * @since curso 2023-2024
	 */
	public static void leerFicheroPeliculas(File fichero) {

		Peliculas peliculas = null;

		try {
			Scanner scanner = new Scanner(fichero);

			while (scanner.hasNextLine()) {

				String linea = scanner.nextLine();
				String partes[] = linea.split("\\|");

				String titulo = partes[0];
				int año = Integer.parseInt(partes[1]);
				List<String> protagonista = new ArrayList<String>();

				String actores[] = partes[2].split(",");
				String[] protagonistasInfo = partes[3].split(",");

				for (int i = 0; i < actores.length; i++) {
					if (protagonistasInfo[i].equals("s")) {
						protagonista.add(actores[i]);
					}
					String actorSinEspacios = actores[i].replaceAll("\\s", "");
					if (!(Principal.actores.contains(actorSinEspacios))) {
						Principal.actores.add(actorSinEspacios);
					}
				}

				peliculas = new Peliculas(titulo, año, actores, protagonista);
				Principal.peliculas.add(peliculas);

			}
			scanner.close();

		} catch (FileNotFoundException e) {
			LOGGER.error("Fichero de peliculas no encontrado");
		}

	}

}
